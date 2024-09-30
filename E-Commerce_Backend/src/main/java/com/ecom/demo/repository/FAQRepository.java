package com.ecom.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Integer>
{
	List<FAQ> findAllByProductId(int productId);
}
