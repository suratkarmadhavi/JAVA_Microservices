package com.order_service.controller;

import com.order_service.dto.OrderRequestDTO;
import com.order_service.dto.OrderResponseDTO;
import com.order_service.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDto) {
		OrderResponseDTO orderResopnseDto = orderService.createOrder(orderRequestDto);
		return new ResponseEntity<>(orderResopnseDto, HttpStatus.CREATED);
	}

	@Operation(
	        summary = "Get Order by ID",
	        description = "Fetch a specific order from the database using the order ID"
	)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Order found madhavi successfully.",
	                content = @Content(mediaType = "application/json",
	                schema = @Schema(implementation = OrderResponseDTO.class))),

	        @ApiResponse(responseCode = "404", description = "Order not found",
	                content = @Content),

	        @ApiResponse(responseCode = "500", description = "Internal server error",
	                content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
		OrderResponseDTO orderResponseDto = orderService.getOrderById(id);
		return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
		List<OrderResponseDTO> orderResponseDto = orderService.getAllOrders();
		return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

	}
}
