package com.ecom.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter 
{
	
	 @Value("${app.client.url}")
	 private String clientAppUrl = "";

	 public SimpleCorsFilter() {}

	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	            throws IOException, ServletException, java.io.IOException 
	 {
		 HttpServletResponse response = (HttpServletResponse) res;
	     HttpServletRequest request = (HttpServletRequest) req;
	     Map<String, String> map = new HashMap<>();
	     String originHeader = request.getHeader("origin");
	     response.setHeader("Access-Control-Allow-Origin", originHeader);
	     response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "*");

	     if ("OPTIONS".equalsIgnoreCase(request.getMethod()))	{	response.setStatus(HttpServletResponse.SC_OK);	} 
	     else	{	chain.doFilter(req, res);	}
	 }

	 public void init(FilterConfig filterConfig)  {}

	 public void destroy() {}

	@Override
	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

}
