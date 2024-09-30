package com.ecom.demo.services.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.ecom.demo.dto.AddProductInCartDto;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.dto.PlaceOrderDto;

public interface CartService 
{
	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	OrderDto getCartByUserId(int userId);
	
	OrderDto applyCoupon(int userId, String code);
	
	OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	
	List<OrderDto> getMyPlacedOrders(int userId);
	
	OrderDto searchOrderByTrackingId(UUID trackingId);
}
