package com.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order_service.dto.OrderRequestDTO;
import com.order_service.dto.OrderResponseDTO;
import com.order_service.entity.Order;

@Service
public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDto);
    
    OrderResponseDTO getOrderById(Long id);

	List<OrderResponseDTO> getAllOrders();
}
