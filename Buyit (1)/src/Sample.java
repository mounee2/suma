package com.pega.validation.sync;

/*
 * Copyright (c) 2019 Pegasystems Inc.
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pega.api.entity.ValidationStatus;
import com.pega.validation.SynchronousValidation;
import com.pega.validation.annotation.ValidationGroups;
import com.pega.validation.annotation.ValidationMetaData;
import com.pega.validation.aws.client.provider.entity.Product;
import com.pega.validation.client.provider.ClientProvider;
import com.pega.validation.constants.ValidationGroup;
import com.pega.validation.constants.ValidationMode;
import com.pega.validation.entity.ValidationRequest;
import com.pega.validation.entity.ValidationResponse;
import com.pega.validation.exception.ValidationException;

/**
 * <p>
 * This validation checks whether there are any duplicate services present in
 * the products List at the cmdb node path.
 * </p>
 */

@ValidationMetaData(validationName = "DuplicateServicesInProductMetadata", owner = "Celestials",
    email = "CelestialsScrumTeam@pega.com",
    description = "Checks if duplicate entries of a service is present in products metadata at the cmdb path",
    validationMode = ValidationMode.Synchronous)
@ValidationGroups(validationGroups = { ValidationGroup.PreInfraUpgrade, ValidationGroup.PrePatchUpgrade,
									   ValidationGroup.PrePlatformUpgrade, ValidationGroup.PostInfraUpgrade })
public class DuplicateServiceCheck extends SynchronousValidation {

    @Override
    public ValidationResponse validate(ValidationRequest request) throws ValidationException {

        ValidationResponse.Builder validationResponse = new ValidationResponse.Builder();
        ClientProvider clientProvider = getClientProvider();
        List<Product> productsMetadata = clientProvider.provideCopsClientProvider().getCopsClient()
                .getProductsMetadata(request.getPath());
        Map<String, List<String>> failedValidation = new HashMap<>();
        if (!productsMetadata.isEmpty()) {
            Map<String, List<String>> servicesToProduct = getServicesToProductMap(productsMetadata);
            servicesToProduct.entrySet().stream().filter(serviceToProduct -> serviceToProduct.getValue().size() != 1)
                    .forEach(entry -> failedValidation.put(entry.getKey(), entry.getValue()));
            if (failedValidation.isEmpty()) {
                validationResponse.withValidationStatus(ValidationStatus.SUCCESS);
            } else {
                validationResponse.withErrorMessage(buildErrorMessage(failedValidation))
                .withRecommendation(buildrecommendationMessage(failedValidation))
                        .withValidationStatus(ValidationStatus.FAILED);
            }
        }
        return validationResponse.build();
    }

    private static String buildErrorMessage(Map<String, List<String>> failedValidation) {
        StringBuilder errorMessage = new StringBuilder();
        failedValidation.entrySet().stream().forEach(entry -> errorMessage.append(buildErrorMessageForEntry(entry)));
        return errorMessage.toString();
    }

    private static String buildErrorMessageForEntry(Entry<String, List<String>> entry) {
        return  "Service [" + entry.getKey() + "] is present in multiple products "
                + entry.getValue() + ". ";
    }

    private static S
    tring buildrecommendationMessage(Map<String, List<String>> failedValidation) {
        StringBuilder recommendationMessage = new StringBuilder();
        failedValidation.entrySet().stream()
                .forEach(entry -> recommendationMessage.append(buildRcmndMsgForEntry(entry)));
        return recommendationMessage.toString();
    }

    private static String buildRcmndMsgForEntry(Entry<String, List<String>> entry) {
    	return "Update the product metadata to delete duplicate entry for " + entry.getKey()
        + " from Add-on among the products- " + entry.getValue() + ". ";   
}

private static Map<String, List<String>> getServicesToProductMap(List<Product> productsMetadata) {
Map<String, List<String>> servicesToProduct = new HashMap<>();
productsMetadata.stream().forEach(product -> product.getServices().forEach((String service) -> {
    if (!servicesToProduct.containsKey(service)) {
        List<String> productIds = new ArrayList<>();
        productIds.add(product.getId());
        servicesToProduct.put(service, productIds);
    } else {
        servicesToProduct.get(service).add(product.getId());
    }
}));
return servicesToProduct;

}

}






-----------------------------------------------





private EnvironmentConfigurations retrieveEnvironmentConfigurations(String envPath, ClientProvider clientProvider) {
	Map<String, List<String>> paramsWithService = new HashMap<>();
	paramsWithService.put(PEGA_7, Arrays.asList(PEGA_VERSION));
	paramsWithService.put(PEGA_UTIL_TIER, Arrays.asList(PEGA_UTIL_FLEET_CAPACITY, ENABLE_STATIC_IPS));
	paramsWithService.put(PEGA_APP_TIER, Arrays.asList(PEGA_APP_FLEET_CAPACITY, ENABLE_STATIC_IPS));
	paramsWithService.put(RDSDatabase, Arrays.asList(DBAllocatedStorage));
	return clientProvider.provideCopsClientProvider().getCopsClient()
			.getEnvironmentConfigurationsForService(envPath, paramsWithService);
}






-----------------------------------------------



private static final String PEGA_7 = "Pega7";
private static final String PEGA_UTIL_TIER = "PegaUtilTier";
//private static final String PEGA_APP_TIER = "PegaAppTier";
private static final String PEGA_VERSION = "PegaVersion";
//private static final String PEGA_UTIL_FLEET_CAPACITY = "PegaUtilFleetCapacity";
//private static final String PEGA_APP_FLEET_CAPACITY = "PegaFleetCapacity";
//private static final String ENABLE_STATIC_IPS = "EnableStaticIPs";
private static final String VERSION_8_1_4 = "8.1.4";
    
