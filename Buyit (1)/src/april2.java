package com.pega.validation.entity;

/**
 * Java Entity class used as input request for each validation
 * @author wunul
 *
 */
public class ValidationRequest {
	
	private String path;
	private String validationName;
	private String executionId;
	private String version = "";
	
	public ValidationRequest() {
		//TODO
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getValidationName() {
		return validationName;
	}
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getVersion() {
		return version == null?"":version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
	}