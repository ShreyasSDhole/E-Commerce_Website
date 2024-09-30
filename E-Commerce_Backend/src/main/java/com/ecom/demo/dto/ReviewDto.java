package com.ecom.demo.dto;

import org.springframework.web.multipart.MultipartFile;

//import lombok.Data;

//@Data
public class ReviewDto 
{
	
	private int id; 
	private int rating;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
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
	public int getProduct() {
		return productId;
	}
	public void setProductId(int product) {
		this.productId = product;
	}
	private MultipartFile img; 
	private byte[] returnedImg;
	private int userId;
	private int productId;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getProductId() {
		return productId;
	}
}
