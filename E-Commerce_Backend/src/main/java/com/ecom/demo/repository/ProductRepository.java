package com.ecom.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	List<Product> findAllByNameContaining(String title);
}
