package com.ecom.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.User;
import com.ecom.demo.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole admin);
	
}
