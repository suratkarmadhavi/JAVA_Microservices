package com.user_service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.user_service.dto.UserRequestDTO;
import com.user_service.dto.UserResponseDTO;
import com.user_service.entity.User;
import com.user_service.repository.UserRepository;
import com.user_service.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDTO registerUser(UserRequestDTO userRequestDto) {

		if (userRepository.existsByEmail(userRequestDto.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setUsername(userRequestDto.getUsername());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		user.setEmail(userRequestDto.getEmail());
		user.setRole(userRequestDto.getRole());

		userRepository.save(user);

		UserResponseDTO userResponseDto = new UserResponseDTO();
		userResponseDto.setUsername(user.getUsername());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setId(user.getId());
		userResponseDto.setRole(user.getRole());
		System.out.println(userResponseDto);

		return userResponseDto;
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return mapToDTO(user);
	}

	private UserResponseDTO mapToDTO(User user) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(user.getId());
		userResponseDTO.setRole(user.getRole());
		userResponseDTO.setUsername(user.getUsername());
		userResponseDTO.setEmail(user.getEmail());
		return userResponseDTO;
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToDTO).toList();
	}

	@Override
	public UserResponseDTO getUserByUserName(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found with username" + username));

		return mapToDTO(user);
	}

	@Override
	public UserResponseDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this Email ID"));
		return mapToDTO(user);

	}

}
