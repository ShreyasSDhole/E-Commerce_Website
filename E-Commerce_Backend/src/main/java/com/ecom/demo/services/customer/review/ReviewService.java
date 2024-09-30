package com.ecom.demo.services.customer.review;

import java.io.IOException;

import com.ecom.demo.dto.OrderedProductsResponseDto;
import com.ecom.demo.dto.ReviewDto;

public interface ReviewService 
{
	OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(int orderId);
	
	ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
