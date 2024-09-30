package com.ecom.demo.services.customer.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.demo.dto.OrderedProductsResponseDto;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.dto.ReviewDto;
import com.ecom.demo.entity.CartItems;
import com.ecom.demo.entity.Order;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Review;
import com.ecom.demo.entity.User;
import com.ecom.demo.repository.OrderRepository;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.repository.ReviewRepository;
import com.ecom.demo.repository.UserRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService
{
	private final OrderRepository orderRepository;
	
	private final ProductRepository productRepository;
	
	private final UserRepository userRepository;
	
	private final ReviewRepository reviewRepository;

	public ReviewServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
			UserRepository userRepository, ReviewRepository reviewRepository) {
		super();
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
	}
	
	public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(int orderId)
	{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		
		OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
		
		if(optionalOrder.isPresent())
		{
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList = new ArrayList<>();
			
			for(CartItems cartItems : optionalOrder.get().getCartItems())
			{
				ProductDto productDto = new ProductDto();
				
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				productDto.setByteImg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			
			orderedProductsResponseDto.setProductDtoList(productDtoList);
		}
		return orderedProductsResponseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException
	{
		Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Review review = new Review();
			
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			
			return reviewRepository.save(review).getDto();
		}
		return null;
	}
}
