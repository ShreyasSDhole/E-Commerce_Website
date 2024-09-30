package com.ecom.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>
{
	List<Review> findAllByProductId(int productId);
}
