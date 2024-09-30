package com.ecom.demo.services.customer;

import java.util.List;

import com.ecom.demo.dto.ProductDetailDto;
import com.ecom.demo.dto.ProductDto;

public interface CustomerProductService 
{
	List<ProductDto> getAllProducts();

	List<ProductDto> searchProductByTitle(String name);

	ProductDetailDto getProductDetailbyId(int productId);
}
