package com.pega.validation.aws.client.provider.entity;
import java.util.List;

public class Product {
	private String id;
	private String version;
	private String purpose;
	private List<String> services;

	public Product() {}

	public Product(String id, String version, List<String> services) {
		this.id = id;
		this.version = version;
		this.services = services;
	}
	
	public Product(String id, String version, String purpose, List<String> services) {
		this.id = id;
		this.version = version;
		this.services = services;
		this.purpose = purpose;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}
	
	public boolean hasService(String service) {
		return getServices().stream().filter(serviceName -> serviceName.equals(service)).findFirst().isPresent();
	}

}