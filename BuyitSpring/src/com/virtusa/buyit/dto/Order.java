package com.virtusa.buyit.dto;

import java.util.Date;
import java.util.List;



public class Order {
    private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private Date orderDate;
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	private List<Product> orderedProductsList;
	
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Product> getOrderedProductsList() {
		return orderedProductsList;
	}
	public List<Product> setOrderedProductsList(List<Product> orderedProductsList) {
		return this.orderedProductsList = orderedProductsList;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", customer=" + customer + ", orderedProductsList="
				+ orderedProductsList + "]";
	}
	
}
