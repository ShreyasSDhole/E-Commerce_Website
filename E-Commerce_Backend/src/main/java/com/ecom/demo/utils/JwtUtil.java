package com.ecom.demo.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil 
{
	public static final String SECRET = "Shreyas_Key";
	
	public String generateToken(String userName)
	{
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	// Here, the builder method block is deprecated for this current version, hence it is presented with a stroke.
	// In near future, the update will be implemented for the given.
	private String createToken(Map<String, Object> claims, String userName) 
	{
		return Jwts.builder().addClaims(claims).setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
				.signWith(getSignKey(), SignatureAlgorithm.ES256).compact();
	}

	private Key getSignKey() 
	{
		byte[] keybytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public String extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) 
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) 
	{
		return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) 
	{
		return extractClaim(token, Claims::getExpiration);
	}	
	
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
