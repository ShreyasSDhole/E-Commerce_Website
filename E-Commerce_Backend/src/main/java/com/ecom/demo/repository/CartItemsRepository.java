package com.ecom.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.demo.entity.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer>
{

	Optional<CartItems> findByProductIdAndOrderIdAndUserId(int productId, int id, int userId);
	
}
