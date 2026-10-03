package com.product_service.service;


import org.springframework.stereotype.Service;

import com.product_service.dto.ProductRequestDTO;
import com.product_service.dto.ProductResponseDTO;
import com.product_service.entity.Product;

import java.util.List;


public interface ProductService {
	
	
	 ProductResponseDTO saveProduct(ProductRequestDTO productDTO);
	 
	 ProductResponseDTO getProductById(Long id);
	 
	 List<ProductResponseDTO> getAllProducts();
	 
	 List<ProductResponseDTO> getProductByName(String productName);



	

}
