package com.ecom.demo.services.admin.coupon;

import java.util.List;

import com.ecom.demo.entity.Coupon;

public interface AdminCouponService 
{
	Coupon createCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();
}
