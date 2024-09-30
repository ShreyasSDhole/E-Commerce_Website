package com.ecom.demo.dto;

import java.util.Date;

//import lombok.Data;

//@Data
public class CouponDto 
{
	private int id;
	private String name;
	private String code;
	private double discount;
	private Date expirationDate;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "CouponDto [id=" + id + ", name=" + name + ", code=" + code + ", discount=" + discount
				+ ", expirationDate=" + expirationDate + "]";
	}
	
	
}
