package com.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth_service.dto.UserResponseDTO;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/users/getUserByEmail/{email}")
    UserResponseDTO getUserByEmail(
            @PathVariable String email);
}