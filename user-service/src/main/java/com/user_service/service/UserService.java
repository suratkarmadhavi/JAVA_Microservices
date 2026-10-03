package com.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.user_service.dto.UserRequestDTO;
import com.user_service.dto.UserResponseDTO;


public interface UserService {
	
	UserResponseDTO registerUser(UserRequestDTO userRequestDto);
	
	UserResponseDTO getUserById(Long id);
	
	List<UserResponseDTO> getAllUsers();

	UserResponseDTO getUserByUserName(String username);
	
	UserResponseDTO getUserByEmail(String email);

}
