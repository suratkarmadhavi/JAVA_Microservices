package com.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order_service.dto.ProductResponseDTO;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceClient {

	@GetMapping("/products/productId/{id}")
	ProductResponseDTO getProductById(@PathVariable("id") Long id);

}
