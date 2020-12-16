package com.virtusa.buyit.dto;




public class Product {

	private int productId;

	private String description;
	private String finish;
	private double standardPrice;
	private int quantity;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product( String description, String finish, double standardPrice) {
		super();
		
		this.description = description;
		this.finish = finish;
		this.standardPrice = standardPrice;
	}
	public Product(int productId, String description, String finish, double standardPrice) {
		super();
		this.productId=productId;
		this.description = description;
		this.finish = finish;
		this.standardPrice = standardPrice;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	

}



