package com.ecom.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecom.demo.filters.JwtRequestFilter;

//import lombok.RequiredArgsConstructor;

@Configuration
//@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration 
{
	
	private final JwtRequestFilter authFilter;	// put final at the end 
    
    public WebSecurityConfiguration(JwtRequestFilter authFilter) 
    {
		this.authFilter = authFilter;
	}

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/authenticate", "/sign-up", "/order/**").permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/api/**").authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
	}

    @Bean
    PasswordEncoder passwordEncoder() {	return new BCryptPasswordEncoder();	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
    	return config.getAuthenticationManager();
    }
    
}
