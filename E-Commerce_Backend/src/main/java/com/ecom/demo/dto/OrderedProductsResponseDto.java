package com.ecom.demo.dto;

import java.util.List;

//import lombok.Data;

//@Data
public class OrderedProductsResponseDto 
{
	private List<ProductDto> productDtoList;
	private double orderAmount;
	
	public List<ProductDto> getProductDtoList() {
		return productDtoList;
	}
	public void setProductDtoList(List<ProductDto> productDtoList) {
		this.productDtoList = productDtoList;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	
	
}
