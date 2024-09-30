package com.ecom.demo.dto;

import com.ecom.demo.enums.UserRole;

//import lombok.Data;

//@Data
public class UserDto 
{
	private int id;
	private String email;
	private String name;
	private UserRole userRole;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
}
