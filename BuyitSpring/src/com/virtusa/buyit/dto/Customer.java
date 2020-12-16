package com.virtusa.buyit.dto;

public class Customer {


	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private int postalcode;	
	private String userName;
	private String password;	
	private String email;
	private long mobileNumber;
	private String gender;
	
	public Customer( String name, String address, String city, String state, int postalcode, String userName,
			String password, String email, int mobileNumber, String gender) {
		super();
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", postalcode=" + postalcode + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", gender=" + gender + "]";
	}
	
	
}
