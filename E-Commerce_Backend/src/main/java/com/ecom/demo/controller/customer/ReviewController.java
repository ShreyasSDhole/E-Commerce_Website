package com.ecom.demo.controller.customer;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.dto.OrderedProductsResponseDto;
import com.ecom.demo.dto.ReviewDto;
import com.ecom.demo.services.customer.review.ReviewService;

//import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController 
{
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) 
	{
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable int orderId)
	{
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException
	{
		ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
		
		if(reviewDto1 == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong!");
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
	}
}
