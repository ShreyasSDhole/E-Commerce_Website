package com.ecom.demo.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.demo.entity.User;
import com.ecom.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User> optionalUser = userRepository.findFirstByEmail(username);
		if (optionalUser.isEmpty())
			throw new UsernameNotFoundException("Username Not Found!", null);
		
		return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(), 
					optionalUser.get().getPassword(), new ArrayList<>());
	}

}
