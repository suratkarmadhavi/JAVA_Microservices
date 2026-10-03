package com.product_service.exception;

public class ProductNotFoundException extends RuntimeException {
	 public ProductNotFoundException(Long id ) {
		 super("Product not found with ID: "+ id);
	 }

	public ProductNotFoundException(String id) {
		super("Product not found with ID: "+ id);
	}
}
