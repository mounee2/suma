package com.pega.validation.sync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import com.pega.api.entity.ValidationStatus;
import com.pega.validation.SynchronousValidation;
import com.pega.validation.annotation.ValidationGroups;
import com.pega.validation.annotation.ValidationMetaData;
import com.pega.validation.aws.client.provider.entity.EnvironmentConfigurations;
import com.pega.validation.client.provider.ClientProvider;
import com.pega.validation.constants.ValidationGroup;
import com.pega.validation.constants.ValidationMode;
import com.pega.validation.entity.ValidationRequest;
import com.pega.validation.entity.ValidationResponse;
import com.pega.validation.exception.ValidationException;
import com.pega.validation.utils.Version;

/**
 * <p>
 * This validation checks whether PegaAppTier and PegaUtilTier fleet capacity is
 * less than number of EIPs for these Tiers.
 * </p>
 */
@ValidationMetaData(owner = "Celestials", email = "CelestialsScrumTeam@pega.com", validationName = "EIPValidation", validationMode = ValidationMode.Synchronous, description = "Validate whether PegaAppTier and PegaUtilTier fleet capacity is less than number of EIPs for these Tiers")
@ValidationGroups(validationGroups = { ValidationGroup.PreInfraUpgrade, ValidationGroup.PrePlatformUpgrade,
		ValidationGroup.PrePatchUpgrade, ValidationGroup.PreDbUpgrade, ValidationGroup.PreInstanceClusterRestart })
public class EIPValidation extends SynchronousValidation {

	private static final String PEGA_7 = "Pega7";
	private static final String PEGA_UTIL_TIER = "PegaUtilTier";
	private static final String PEGA_APP_TIER = "PegaAppTier";
	private static final String PEGA_VERSION = "PegaVersion";
	private static final String PEGA_UTIL_FLEET_CAPACITY = "PegaUtilFleetCapacity-";
	private static final String PEGA_APP_FLEET_CAPACITY = "PegaFleetCapacity";
	private static final String ENABLE_STATIC_IPS = "EnableStaticIPs";
	private static final String VERSION_8_1_4 = "8.1.4";

	@Override
	public ValidationResponse validate(ValidationRequest request) throws ValidationException {

		ValidationResponse.Builder validationResponse = new ValidationResponse.Builder();
		ClientProvider clientProvider = getClientProvider();

		EnvironmentConfigurations envConfigurations = retrieveEnvironmentConfigurations(request.getPath(),
				clientProvider);

		List<String> failedValidation = new ArrayList<>();

		if (!validateEIPsForUtilTier(envConfigurations)) {
			failedValidation.add(PEGA_UTIL_TIER);
		}

		if (!validateEIPsForAppTier(envConfigurations)) {
			failedValidation.add(PEGA_APP_TIER);
		}

		if (failedValidation.isEmpty()) {
			validationResponse.withValidationStatus(ValidationStatus.SUCCESS);
		} else {
			validationResponse.withValidationStatus(ValidationStatus.FAILED)
					.withErrorMessage(buildErrorMessage(request.getPath(), failedValidation))
					.withRecommendation(buildRecommendationMessage(request.getPath(), failedValidation));
		}

		return validationResponse.build();
	}

	private EnvironmentConfigurations retrieveEnvironmentConfigurations(String envPath, ClientProvider clientProvider) {
		Map<String, List<String>> paramsWithService = new HashMap<>();
		paramsWithService.put(PEGA_7, Arrays.asList(PEGA_VERSION));
		paramsWithService.put(PEGA_UTIL_TIER, Arrays.asList(PEGA_UTIL_FLEET_CAPACITY, ENABLE_STATIC_IPS));
		paramsWithService.put(PEGA_APP_TIER, Arrays.asList(PEGA_APP_FLEET_CAPACITY, ENABLE_STATIC_IPS));
		return clientProvider.provideCopsClientProvider().getCopsClient()
				.getEnvironmentConfigurationsForService(envPath, paramsWithService);
	}

	private boolean validateEIPsForUtilTier(EnvironmentConfigurations envConfigurations) {
		final int NUMBER_OF_EIP_TILL_PEGA_813 = 2;
		final int NUMBER_OF_EIP_ONWARDS_PEGA_814 = 4;
		boolean enableStaticIPs = Boolean
				.parseBoolean(envConfigurations.getParameter(PEGA_UTIL_TIER + ":" + ENABLE_STATIC_IPS));

		if (enableStaticIPs) {
			Version pegaVersion = new Version(envConfigurations.getParameter(PEGA_7 + ":" + PEGA_VERSION));
			int pegaUtilFleetCapacity = Integer
					.parseInt(envConfigurations.getParameter(PEGA_UTIL_TIER + ":" + PEGA_UTIL_FLEET_CAPACITY));

			if (pegaVersion.compareTo(new Version(VERSION_8_1_4)) < 0) {
				if (pegaUtilFleetCapacity >= NUMBER_OF_EIP_TILL_PEGA_813) {
					return Boolean.FALSE;
				}
			} else {
				if ((3 * pegaUtilFleetCapacity) >= NUMBER_OF_EIP_ONWARDS_PEGA_814) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}

	private boolean validateEIPsForAppTier(EnvironmentConfigurations envConfigurations) {
		final int NUMBER_OF_EIP_APP_TIER = 10;
		boolean enableStaticIPs = Boolean
				.parseBoolean(envConfigurations.getParameter(PEGA_APP_TIER + ":" + ENABLE_STATIC_IPS));

		if (enableStaticIPs) {
			String pegaFleetCapacity = envConfigurations.getParameter(PEGA_APP_TIER + ":" + PEGA_APP_FLEET_CAPACITY);
			if (Integer.parseInt(pegaFleetCapacity) >= NUMBER_OF_EIP_APP_TIER) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}

	private static String buildErrorMessage(String envPath, List<String> failedValidation) {
		return String.format(
				"EIPValidation failed for services : %s because fleet capacity equal or gretear than number of EIPs for environment at CMDBPath: %s",
				failedValidation, envPath);
	}

	private static String buildRecommendationMessage(String envPath, List<String> failedValidation) {
		return String.format(
				"Update services : %s to reduce FleetCapacity less than number of EIPs for environment at CMDBPath: %s",
				failedValidation, envPath);
	}
}


