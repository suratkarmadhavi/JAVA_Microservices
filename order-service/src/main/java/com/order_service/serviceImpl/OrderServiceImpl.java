package com.order_service.serviceImpl;

import com.ecommerce.events.ProductRequestEvent;
import com.order_service.client.ProductServiceClient;
import com.order_service.dto.OrderRequestDTO;
import com.order_service.dto.OrderResponseDTO;
import com.order_service.dto.ProductResponseDTO;
import com.order_service.entity.Order;
import com.order_service.exception.OrderNotFoundException;
import com.order_service.kafka.producer.OrderKafkaProducer;
import com.order_service.repository.OrderRepository;
import com.order_service.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private OrderKafkaProducer producer;

	// Circuit breaker / retry

	// @Override
//	@CircuitBreaker(name = "productService", fallbackMethod = "productServiceFallback")
//	@Retry(name = "productService", fallbackMethod = "productServiceFallback")
//	public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDto) {
//
//		System.out.println("Calling Product Service...");
//
//		ProductResponseDTO productResponseDto = productServiceClient.getProductById(orderRequestDto.getProductId());
//
//		if (productResponseDto == null) {
//			throw new RuntimeException("Product not found");
//		}
//
//		double totalPrice = productResponseDto.getProductPrice() * orderRequestDto.getQuantity();
//
//		Order order = new Order();
//		order.setProductId(orderRequestDto.getProductId());
//		order.setQuantity(orderRequestDto.getQuantity());
//		order.setTotalPrice(totalPrice);
//
//		Order savedOrder = repository.save(order);
//
//		return mapToDTO(savedOrder);
//
//	}
//kafka
	@Override
	public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDto) {

		System.out.println("Calling Product Service...");

		Order order = new Order();
		order.setProductId(orderRequestDto.getProductId());
		order.setQuantity(orderRequestDto.getQuantity());
		order.setTotalPrice(0.0);

		Order savedOrder = repository.save(order);
		
		ProductRequestEvent event = new ProductRequestEvent(
		        savedOrder.getId(),
		        savedOrder.getProductId(),
		        savedOrder.getQuantity()
		);
				
		
		 producer.sendProductRequest(event);

		return mapToDTO(savedOrder);

	}

//	@Override
//	@TimeLimiter(name = "productService", fallbackMethod = "timeLimiterFallback")
//	public CompletableFuture<OrderResponseDTO> createOrder(OrderRequestDTO orderRequestDto) {
//
//	    return CompletableFuture.supplyAsync(() -> {
//
//	        System.out.println("Calling Product Service...");
//
//	        ProductResponseDTO product =
//	                productServiceClient.getProductById(orderRequestDto.getProductId());
//
//	        double totalPrice = product.getProductPrice() * orderRequestDto.getQuantity();
//
//	        Order order = new Order();
//	        order.setProductId(orderRequestDto.getProductId());
//	        order.setQuantity(orderRequestDto.getQuantity());
//	        order.setTotalPrice(totalPrice);
//
//	        Order savedOrder = repository.save(order);
//
//	        return mapToDTO(savedOrder);
//	    });
//	}

	
	@Override
	@Cacheable(value = "orders", key = "#id")
	public OrderResponseDTO getOrderById(Long id) {
	    System.out.println("Fetching order from DATABASE...");

		Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		return mapToDTO(order);

	}

	private OrderResponseDTO mapToDTO(Order savedOrder) {
		if (savedOrder == null) {
			return null;
		}
		return new OrderResponseDTO(savedOrder.getId(), savedOrder.getProductId(), savedOrder.getQuantity(),
				savedOrder.getTotalPrice());
	}

	@Override
	public List<OrderResponseDTO> getAllOrders() {
		List<Order> order = repository.findAll();
		return order.stream().map(this::mapToDTO).toList();
	}

	public OrderResponseDTO productServiceFallback(OrderRequestDTO orderRequestDto, Exception ex) {

		System.out.println("Product service is down. Fallback executed.");

		OrderResponseDTO response = new OrderResponseDTO();
		response.setProductId(orderRequestDto.getProductId());
		response.setQuantity(orderRequestDto.getQuantity());
		response.setTotalPrice(0.0);

		return response;

	}
}
