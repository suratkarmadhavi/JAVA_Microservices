package com.auth_service.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message="Email is required")
	private String email;
	
	
	@NotBlank(message="Password is required")
    private String password;

    public LoginRequest() {
    }

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}

	public LoginRequest(@NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

  
}