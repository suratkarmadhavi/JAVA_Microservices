package com.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.auth_service.client.UserClient;
import com.auth_service.dto.LoginRequest;
import com.auth_service.dto.UserResponseDTO;
import com.auth_service.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserClient userClient;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		UserResponseDTO userResponseDTO;
		try {
			userResponseDTO = userClient.getUserByEmail(loginRequest.getEmail());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		}

		if (passwordEncoder.matches(loginRequest.getPassword(), userResponseDTO.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}

		// Generate JWT Token
		String token = jwtUtil.generateToken(String.valueOf(userResponseDTO.getId()), userResponseDTO.getRole());
		return ResponseEntity.ok(token);

	}
}