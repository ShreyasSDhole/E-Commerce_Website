package com.ecom.demo.dto;

//import lombok.Data;

//@Data
public class PlaceOrderDto 
{
	private int userId;
	private String address;
	private String orderDescription;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int id) {
		this.userId = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	
	
}
