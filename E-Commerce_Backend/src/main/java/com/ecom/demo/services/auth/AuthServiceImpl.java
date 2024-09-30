package com.ecom.demo.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.demo.dto.SignUpRequest;
import com.ecom.demo.dto.UserDto;
import com.ecom.demo.entity.Order;
import com.ecom.demo.entity.User;
import com.ecom.demo.enums.OrderStatus;
import com.ecom.demo.enums.UserRole;
import com.ecom.demo.repository.OrderRepository;
import com.ecom.demo.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public UserDto createUser(SignUpRequest signUpRequest)
	{
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setName(signUpRequest.getName());
		user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user); 
		
		Order order = new Order();
		order.setAmount(0.0);
		order.setTotalAmount(0.0);
		order.setDiscount(0.0);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		
		return userDto;
	}

	@Override
	public boolean hasUserWithEmail(String email) 
	{
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount()
	{
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount)
		{
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("Admin1");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
