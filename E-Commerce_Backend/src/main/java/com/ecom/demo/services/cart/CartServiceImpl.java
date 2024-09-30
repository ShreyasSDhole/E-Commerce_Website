package com.ecom.demo.services.cart;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.demo.dto.AddProductInCartDto;
import com.ecom.demo.dto.CartItemsDto;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.dto.PlaceOrderDto;
import com.ecom.demo.entity.CartItems;
import com.ecom.demo.entity.Coupon;
import com.ecom.demo.entity.Order;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.User;
import com.ecom.demo.enums.OrderStatus;
import com.ecom.demo.exceptions.ValidationException;
import com.ecom.demo.repository.CartItemsRepository;
import com.ecom.demo.repository.CouponRepository;
import com.ecom.demo.repository.OrderRepository;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService
{
	private OrderRepository orderRepository;
	
	private UserRepository userRepository;
	
	private CartItemsRepository cartItemsRepository;

	private ProductRepository productRepository;
	
	private CouponRepository couponRepository;
	
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
		Optional<CartItems> optionalCartItems = cartItemsRepository
				.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(),
				activeOrder.getId(), addProductInCartDto.getUserId());
		
		if(optionalCartItems.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		else
		{
			Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
			Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
			
			if(optionalProduct.isPresent() && optionalUser.isPresent())
			{
				CartItems cart = new CartItems();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				CartItems updatedCart = cartItemsRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
				activeOrder.getCartItems().add(cart);
				
				orderRepository.save(activeOrder);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(cart);
			}
			else
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found!");
			}
		}
	}
	
	public OrderDto getCartByUserId(int userId)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
		OrderDto orderDto = new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setDiscount(activeOrder.getDiscount());
		
		if(activeOrder.getCoupon() != null)
		{
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
		
		return orderDto;
	}
	
	public OrderDto applyCoupon(int userId, String code)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		Coupon coupon = couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found!"));
		
		if(couponIsExpired(coupon))
		{
			throw new ValidationException("Coupon has expired!");
		}
		
		double discountAmount = ((coupon.getDiscount() / 100) * activeOrder.getTotalAmount());
		double netAmount = activeOrder.getTotalAmount() - discountAmount; 
		
		activeOrder.setAmount(netAmount); 
		activeOrder.setDiscount(discountAmount);
		activeOrder.setCoupon(coupon);
		
		orderRepository.save(activeOrder);
		return activeOrder.getOrderDto();
	}

	private boolean couponIsExpired(Coupon coupon) 
	{
		Date currentDate = new Date();
		Date expirationDate =coupon.getExpirationDate();
		
		return expirationDate != null && currentDate.after(expirationDate);
	}
	
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
		Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
		
		Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), 
												activeOrder.getId(), addProductInCartDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItem.isPresent())
		{
			CartItems cartItem = optionalCartItem.get();
			Product product =  optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());
			
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			
			if(activeOrder.getCoupon() != null)
			{
				double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100) * activeOrder.getTotalAmount());
				double netAmount = activeOrder.getTotalAmount() - discountAmount; 
				
				activeOrder.setAmount(netAmount); 
				activeOrder.setDiscount(discountAmount);
			}
			cartItemsRepository.save(cartItem);
			orderRepository.save(activeOrder);
			
			return activeOrder.getOrderDto();
		}
		return null;
	}
	
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
		Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
		
		Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), 
												activeOrder.getId(), addProductInCartDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItem.isPresent())
		{
			CartItems cartItem = optionalCartItem.get();
			Product product =  optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
			
			cartItem.setQuantity(cartItem.getQuantity() - 1);
			
			if(activeOrder.getCoupon() != null)
			{
				double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100) * activeOrder.getTotalAmount());
				double netAmount = activeOrder.getTotalAmount() - discountAmount; 
				
				activeOrder.setAmount(netAmount); 
				activeOrder.setDiscount(discountAmount);
			}
			cartItemsRepository.save(cartItem);
			orderRepository.save(activeOrder);
			
			return activeOrder.getOrderDto();
		}
		return null;
	}
	
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
		Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
		
		if(optionalUser.isPresent())
		{
			activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
			activeOrder.setAddress(placeOrderDto.getAddress());
			activeOrder.setDate(new Date());
			activeOrder.setOrderStatus(OrderStatus.Placed);
			activeOrder.setTrackingId(UUID.randomUUID());
			
			orderRepository.save(activeOrder);
			
			Order order = new Order();
			order.setAmount(0.0);
			order.setTotalAmount(0.0);
			order.setDiscount(0.0);
			order.setUser(optionalUser.get());
			order.setOrderStatus(OrderStatus.Pending);
			orderRepository.save(order);
			
			return activeOrder.getOrderDto();
		}	
		return null;
	}
	
	public List<OrderDto> getMyPlacedOrders(int userId)
	{
		return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped,
				OrderStatus.Delivered)).stream().map(Order::getOrderDto).collect(Collectors.toList());
	}
	
	public OrderDto searchOrderByTrackingId(UUID trackingId)
	{
		Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);
		
		if(optionalOrder.isPresent())
		{
			return optionalOrder.get().getOrderDto();
		}
		return null;
		
	}
	
}
