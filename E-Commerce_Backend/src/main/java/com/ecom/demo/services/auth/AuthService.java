package com.ecom.demo.services.auth;

import com.ecom.demo.dto.SignUpRequest;
import com.ecom.demo.dto.UserDto;

public interface AuthService 
{
	UserDto createUser(SignUpRequest signUpRequest);

	boolean hasUserWithEmail(String email);
}
