package com.ecom.demo.dto;

import java.util.Arrays;

//import lombok.Data;

//@Data
public class CartItemsDto 
{
	private int id;
	private double price;
	private int quantity;
	private int productId;
	private int orderId;
	private String productName;
	private byte[] returnedImg;
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public byte[] getReturnedImg() {
		return returnedImg;
	}
	public void setReturnedImg(byte[] returnedImg) {
		this.returnedImg = returnedImg;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "CartItemsDto [id=" + id + ", price=" + price + ", quantity=" + quantity + ", productId=" + productId
				+ ", orderId=" + orderId + ", productName=" + productName + ", returnedImg="
				+ Arrays.toString(returnedImg) + ", userId=" + userId + "]";
	}
	
	
	
}
