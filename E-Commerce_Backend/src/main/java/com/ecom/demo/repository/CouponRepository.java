package com.ecom.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>
{
	public boolean existsByCode(String code);

	public Optional<Coupon> findByCode(String code);  
}
