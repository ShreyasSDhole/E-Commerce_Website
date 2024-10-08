package com.ecom.demo.controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.dto.AuthenticationRequest;
import com.ecom.demo.dto.SignUpRequest;
import com.ecom.demo.dto.UserDto;
import com.ecom.demo.entity.User;
import com.ecom.demo.repository.UserRepository;
import com.ecom.demo.services.auth.AuthService;
import com.ecom.demo.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
public class AuthController 
{
	public static final String HEADER_STRING = "Bearer";

	public static final String TOKEN_PREFIX = "Authorization";

	private final AuthenticationManager authenticationManager;	//----
	
	private final UserDetailsService userDetailsService;			//----	
	
	private final UserRepository userRepository;					//----
	
	private final JwtUtil jwtUtil;		
	
	private final AuthService authService;
	
	public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			UserRepository userRepository, JwtUtil jwtUtil, AuthService authService) 
	{
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest
											, HttpServletResponse response) throws IOException, JSONException
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
											authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect Username or Password!");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		if(optionalUser.isEmpty())
		{
			response.getWriter().write(new JSONObject()
					.put("userId", optionalUser.get().getId())
					.put("role", optionalUser.get().getRole())
					.toString()
				);	
			
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " +
						"X-Requested-With, Content-Type, Accept, X-Custom-header");
			
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
		}
	}
	
	
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest)
	{
		if(authService.hasUserWithEmail(signUpRequest.getEmail()))
		{
			return new ResponseEntity<>("Username already exists!", HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto userDto = authService.createUser(signUpRequest);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	
}
