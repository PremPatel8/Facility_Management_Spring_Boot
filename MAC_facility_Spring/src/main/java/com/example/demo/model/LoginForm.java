package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
	
	@NotNull(message = "Username may not be null")
	@NotBlank(message = "Username may not be blank")
	@Size(min=2, max=30)
	private String username;
	
	@NotNull(message = "Password may not be null")
	@NotBlank(message = "Password may not be blank")
	private String password;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
