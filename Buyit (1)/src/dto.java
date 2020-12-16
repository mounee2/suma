package com.pega.step.validation.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.pega.api.entity.ValidationStatus;
import com.pega.validation.aws.client.provider.CopsClientProvider;
import com.pega.validation.aws.client.provider.entity.Product;
import com.pega.validation.aws.clients.COPSClient;
import com.pega.validation.client.provider.ClientProvider;
import com.pega.validation.entity.ValidationRequest;
import com.pega.validation.entity.ValidationResponse;
import com.pega.validation.exception.ValidationException;
import com.pega.validation.sync.DuplicateServiceCheck;

public class DuplicateServiceCheckTest {
	
	private static final String CMDB_PATH = "/ROOT/PEGACLOUD/DUMMY_NODE_PATH";
	private static final String VALIDATION_NAME = "DuplicateServicesInProductMetadata";
	private static Product PRODUCT_1 = new Product();
	private static Product PRODUCT_2 = new Product();
	private static Product PRODUCT_3 = new Product();
	private static List<Product> PRODUCTS_SUCCESS = new ArrayList<Product>();
	private static List<Product> PRODUCTS_FAILURE = new ArrayList<Product>();
	private static List<Product> EMPTY_PRODUCT = new ArrayList<Product>();
	
	public static final Logger LOG = LogManager.getLogger(DuplicateServiceCheckTest.class);
	@BeforeClass
	public static void initialize(){
		List<String> services1 = new ArrayList<String>();
		List<String> services2 = new ArrayList<String>();
		List<String> services3 = new ArrayList<String>();
		
		services1.add("Service-1");
		services1.add("Service-2");
		services1.add("Service-3");
	
		services2.add("Service-4");
		services2.add("Service-5");
		services2.add("Service-6");
	
		services3.add("Service-7");
		services3.add("Service-1");
		services3.add("Service-2");
		
		PRODUCT_1.setId("product-1");
		PRODUCT_2.setId("product-2");
		PRODUCT_3.setId("product-3");
		PRODUCT_1.setServices(services1);
		PRODUCT_2.setServices(services2);
		PRODUCT_3.setServices(services3);
		
		PRODUCTS_SUCCESS.add(PRODUCT_1);
		PRODUCTS_SUCCESS.add(PRODUCT_2);
		
		PRODUCTS_FAILURE.add(PRODUCT_1);
		PRODUCTS_FAILURE.add(PRODUCT_3);

	}
	
	@Test
	public void testCheckSuccessful() throws ValidationException{
		
		ValidationRequest validationRequest = new ValidationRequest();
		validationRequest.setPath(CMDB_PATH);
		validationRequest.setValidationName(VALIDATION_NAME);
		ClientProvider clientProvider = Mockito.mock(ClientProvider.class);
		COPSClient copsClient = Mockito.mock(COPSClient.class);
		CopsClientProvider copsClientProvider = Mockito.mock(CopsClientProvider.class); 
		
		
		DuplicateServiceCheck duplicateServiceCheck = new DuplicateServiceCheck();
		DuplicateServiceCheck spyDuplicateServiceCheck = Mockito.spy(duplicateServiceCheck);
		Mockito.when(spyDuplicateServiceCheck.getClientProvider()).thenReturn(clientProvider);
		Mockito.when(clientProvider.provideCopsClientProvider()).thenReturn(copsClientProvider);
		Mockito.when(copsClientProvider.getCopsClient()).thenReturn(copsClient);
		Mockito.when(copsClient.getProductsMetadata(CMDB_PATH)).thenReturn(PRODUCTS_SUCCESS);
		
		ValidationResponse res = spyDuplicateServiceCheck.validate(validationRequest);
		Assert.assertTrue("Expecting Success",res.getValidationStatus().equals(ValidationStatus.SUCCESS));
	}
	
	@Test
	public void testCheckFailure() throws ValidationException{
		
		ValidationRequest validationRequest = new ValidationRequest();
		validationRequest.setPath(CMDB_PATH);
		validationRequest.setValidationName(VALIDATION_NAME);
		ClientProvider clientProvider = Mockito.mock(ClientProvider.class);
		COPSClient copsClient = Mockito.mock(COPSClient.class);
		COPSClient copsClient = Mockito.mock(COPSClient.class);
		CopsClientProvider copsClientProvider = Mockito.mock(CopsClientProvider.class); 
		
		
		DuplicateServiceCheck duplicateServiceCheck = new DuplicateServiceCheck();
		DuplicateServiceCheck spyDuplicateServiceCheck = Mockito.spy(duplicateServiceCheck);
		Mockito.when(spyDuplicateServiceCheck.getClientProvider()).thenReturn(clientProvider);
		Mockito.when(clientProvider.provideCopsClientProvider()).thenReturn(copsClientProvider);
		Mockito.when(copsClientProvider.getCopsClient()).thenReturn(copsClient);
		Mockito.when(copsClient.getProductsMetadata(CMDB_PATH)).thenReturn(PRODUCTS_FAILURE);
		
		ValidationResponse res = spyDuplicateServiceCheck.validate(validationRequest);

		Assert.assertTrue("Expecting Failure",res.getValidationStatus().equals(ValidationStatus.FAILED));
	}
	
	@Test
	public void testEmptyProductMetadata() throws ValidationException{
        
        ValidationRequest validationRequest = new ValidationRequest();
        validationRequest.setPath(CMDB_PATH);
        validationRequest.setValidationName(VALIDATION_NAME);
        ClientProvider clientProvider = Mockito.mock(ClientProvider.class);
        COPSClient copsClient = Mockito.mock(COPSClient.class);
        CopsClientProvider copsClientProvider = Mockito.mock(CopsClientProvider.class); 
        
        
        DuplicateServiceCheck duplicateServiceCheck = new DuplicateServiceCheck();
        DuplicateServiceCheck spyDuplicateServiceCheck = Mockito.spy(duplicateServiceCheck);
        Mockito.when(spyDuplicateServiceCheck.getClientProvider()).thenReturn(clientProvider);
        Mockito.when(clientProvider.provideCopsClientProvider()).thenReturn(copsClientProvider);
        Mockito.when(copsClientProvider.getCopsClient()).thenReturn(copsClient);
        Mockito.when(copsClient.getProductsMetadata(CMDB_PATH)).thenReturn(EMPTY_PRODUCT);
        
        ValidationResponse res = spyDuplicateServiceCheck.validate(validationRequest);

        Assert.assertTrue("Expecting Success",res.getValidationStatus().equals(ValidationStatus.SUCCESS));
    }
	
}