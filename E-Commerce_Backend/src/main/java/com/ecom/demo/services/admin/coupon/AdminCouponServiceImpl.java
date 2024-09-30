package com.ecom.demo.services.admin.coupon;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.demo.entity.Coupon;
import com.ecom.demo.exceptions.ValidationException;
import com.ecom.demo.repository.CouponRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService
{
	private final CouponRepository couponRepository;

	public AdminCouponServiceImpl(CouponRepository couponRepository) 
	{
		super();
		this.couponRepository = couponRepository;
	}
	
	public Coupon createCoupon(Coupon coupon)
	{
		if(couponRepository.existsByCode(coupon.getCode()))
		{
			throw new ValidationException("coupon code already exists!");
		}
		return couponRepository.save(coupon);
	}
	
	public List<Coupon> getAllCoupons()
	{
		return couponRepository.findAll();	
	}
}
