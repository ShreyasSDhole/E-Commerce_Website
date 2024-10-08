package com.ecom.demo.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.entity.Coupon;
import com.ecom.demo.exceptions.ValidationException;
import com.ecom.demo.services.admin.coupon.AdminCouponService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/coupons")
//@RequiredArgsConstructor
public class AdminCouponController 
{
	private final AdminCouponService adminCouponService;

	public AdminCouponController(AdminCouponService adminCouponService) {
		super();
		this.adminCouponService = adminCouponService;
	}
	
	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon)
	{
		try
		{
			Coupon createCoupon = adminCouponService.createCoupon(coupon);
			return ResponseEntity.ok(createCoupon);
		}
		catch(ValidationException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Coupon>> getAllCoupons()
	{
		return ResponseEntity.ok(adminCouponService.getAllCoupons());
	}
}
