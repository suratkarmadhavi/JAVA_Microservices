package com.product_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_service.dto.ProductRequestDTO;
import com.product_service.dto.ProductResponseDTO;
import com.product_service.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productDTO) {
		logger.info("Retrived request to save product: {}", productDTO);
		ProductResponseDTO savedProduct = productService.saveProduct(productDTO);
		logger.info("Product Saved Successfully with ID: {}", savedProduct.getProductId());
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@GetMapping("/productId/{id}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id) {
		logger.info("Received request to fetch product with ID: {}", id);
		ProductResponseDTO product = productService.getProductById(id);
		logger.info("Product fetched successfully with ID: {}", id);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/getAllProducts")
	public List<ProductResponseDTO> getAllProducts() {
		logger.info("Received request to fetch the all products");
		List<ProductResponseDTO> products = productService.getAllProducts();
		logger.info("Total Product fetched: {}", products.size());
		return products;

	}

	@GetMapping("/productName/{productName}")
	public ResponseEntity<List<ProductResponseDTO>> getProductByName(@PathVariable String productName) {
		List<ProductResponseDTO> productResponseDto = productService.getProductByName(productName);
		return ResponseEntity.ok(productResponseDto);
	}

}
