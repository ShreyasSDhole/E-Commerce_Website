package com.ecom.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Data;

@Entity
//@Data
@Table(name = "orders")
public class Order 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String orderDescription;
	private Date date;
	private double amount;
	private String address;
	private String payment; 
	private OrderStatus orderStatus;
	private double totalAmount;
	private double discount;
	private UUID trackingId;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "coupon_id", referencedColumnName = "id")
	private Coupon coupon;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItems> cartItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double d) {
		this.amount = d;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double d) {
		this.totalAmount = d;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double d) {
		this.discount = d;
	}

	public UUID getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(UUID trackingId) {
		this.trackingId = trackingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public OrderDto getOrderDto()
	{
		OrderDto orderDto = new OrderDto();
		
		orderDto.setId(id);
		orderDto.setOrderDescription(orderDescription);
		orderDto.setAddress(address);
		orderDto.setTrackingId(trackingId);
		orderDto.setAmount(amount);
		orderDto.setDate(date);
		orderDto.setOrderStatus(orderStatus);
		orderDto.setUserName(user.getName());
		
		if(coupon != null)
		{
			orderDto.setCouponName(coupon.getName());
		}
		
		return orderDto;
	}
	
}
