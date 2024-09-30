package com.ecom.demo.dto;

//import lombok.Data;

//@Data
public class WishlistDto 
{
	private int id;
	private int userId;	
	private int productId;
	private String productName;
	private String productDescription;
	private byte[] returnedImg;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public byte[] getReturnedImg() {
		return returnedImg;
	}
	public void setReturnedImg(byte[] returnedImg) {
		this.returnedImg = returnedImg;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
