package com.pega.validation.aws.client.provider;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.pega.validation.aws.client.provider.entity.EnvironmentCacheService;
import com.pega.validation.aws.client.provider.entity.EnvironmentConfigurations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.retry.PredefinedBackoffStrategies;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.util.StringUtils;
import com.pega.validation.aws.client.provider.entity.CopsClientProviderConfiguration;
import com.pega.validation.aws.client.provider.entity.Product;
import com.pega.validation.aws.clients.COPSClient;
import com.pega.validation.aws.clients.LambdaClient;
import com.pega.validation.aws.clients.StsClient;
import com.pega.validation.common.ValidationServiceCommonConstants;

/**
 * Provider class that initializes a COPSClient object
 * @author wunul
 *
 */
public class CopsClientProvider {

	private static Logger LOG = LogManager.getLogger(CopsClientProvider.class);
	
	private COPSClient copsClient;
	private CopsClientProviderConfiguration config;
	private EnvironmentConfigurations environmentConfigurations;
	private List<Product> products;
	
	public CopsClientProviderConfiguration getConfig() {
		return config;
	}

	public CopsClientProvider(CopsClientProviderConfiguration config) {
		this.config = config;
		try{
			environmentConfigurations = new EnvironmentCacheService(config.getSessionId()).getEnvConfigFromS3();
			products = new EnvironmentCacheService(config.getSessionId()).getProductsMetadataFromS3();
		}catch(AmazonS3Exception | IllegalArgumentException e){
			LOG.warn("An exception occured while fetching from S3");
			environmentConfigurations = new EnvironmentConfigurations();
			products = new ArrayList<Product>();
		}

	}
	
	public COPSClient getCopsClient() {
		if(copsClient == null) {
			copsClient = provideCopsClient();
		}
		return copsClient;
	}

	private COPSClient provideCopsClient() {
		if(copsClient == null) {
			AWSCredentialsProvider credentialsProvider;

			if(StringUtils.isNullOrEmpty(config.getCopsAccountId()) || ValidationServiceCommonConstants.PARAMETER_DEFAULT_VALUE.equals(config.getCopsAccountId())) {
				credentialsProvider = new DefaultAWSCredentialsProviderChain(); 
			} else {
				String copsRoleArn = StringUtils.join("", "arn:aws:iam::",config.getCopsAccountId(),":role/",config.getCopsStackName(),"-CopsCmdbAccessRole");
				
				LOG.info("Using CopsRoleArn : " + copsRoleArn);
				
				AWSCredentials temporaryCredentials = provideStsAwsClient().assumeRole(copsRoleArn, "copsSession");
				credentialsProvider = new AWSStaticCredentialsProvider(temporaryCredentials);
			}
			
			AWSLambda lambdaClient = AWSLambdaClientBuilder.standard().withClientConfiguration(new ClientConfiguration().withRetryPolicy(new RetryPolicy(null, new PredefinedBackoffStrategies.EqualJitterBackoffStrategy(50*1000, 12*1000*60), 30, true)))
					.withCredentials(credentialsProvider).withRegion(config.getCopsRegion()).build(); 
			
			LambdaClient client = new LambdaClient(lambdaClient);

			copsClient = new COPSClient(client, config.getCopsCmdbHandlerName());
			copsClient.setEnvironmentConfigurations(environmentConfigurations);
			copsClient.setProductsMetadata(products);
		}
		
		return copsClient;
	}
	
	private StsClient provideStsAwsClient() {
		AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
				.withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(config.getCopsRegion()).build();
		return new StsClient(stsClient);
	}
}
