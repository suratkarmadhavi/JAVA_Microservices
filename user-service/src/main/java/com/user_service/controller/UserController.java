package com.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user_service.dto.UserRequestDTO;
import com.user_service.dto.UserResponseDTO;
import com.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registerUser")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {

		UserResponseDTO userResponseDTO = userService.registerUser(userRequestDTO);
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);

	}

	@GetMapping("/getUserById/{id}")
	public UserResponseDTO getUserById(@PathVariable Long id) {
		UserResponseDTO userResponseDTO = userService.getUserById(id);

		return userResponseDTO;
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		List<UserResponseDTO> userResponseDTOs = userService.getAllUsers();
		return new ResponseEntity<>(userResponseDTOs, HttpStatus.OK);
	}

	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<UserResponseDTO> getUserByuserName(@PathVariable String username) {
		UserResponseDTO userResponseDTO = userService.getUserByUserName(username);
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email){
		UserResponseDTO userResponseDTO =  userService.getUserByEmail(email);
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
	}
}
