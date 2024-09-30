package com.ecom.demo.dto;

import org.springframework.web.multipart.MultipartFile;

//import lombok.Data;

//@Data
public class ProductDto 
{
	private int id;
	private String name;
	private String description;
	private double price;
	private byte[] byteImg;
	private int categoryId;
	private String categoryName;
	private MultipartFile img;
	private int quantity;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public byte[] getByteImg() {
		return byteImg;
	}
	public void setByteImg(byte[] byteImg) {
		this.byteImg = byteImg;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
