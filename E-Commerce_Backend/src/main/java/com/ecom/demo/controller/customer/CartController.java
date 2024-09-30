package com.ecom.demo.controller.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.dto.AddProductInCartDto;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.dto.PlaceOrderDto;
import com.ecom.demo.exceptions.ValidationException;
import com.ecom.demo.services.cart.CartService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
//@RequiredArgsConstructor
public class CartController 
{
	public final CartService cartService;
	
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return cartService.addProductToCart(addProductInCartDto);
	}
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable int userId)
	{
		OrderDto orderDto = cartService.getCartByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable int userId, @PathVariable String code)
	{
		try
		{
			OrderDto orderDto = cartService.applyCoupon(userId, code);
			return ResponseEntity.ok(orderDto);
		}
		catch(ValidationException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/addition")
	public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
	}
	
	@PostMapping("/deduction")
	public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable int userId)
	{
		return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
	}
	
	
	
	
	
}
