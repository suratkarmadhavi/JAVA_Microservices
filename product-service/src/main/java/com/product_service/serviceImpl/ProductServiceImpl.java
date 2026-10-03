package com.product_service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.product_service.dto.ProductRequestDTO;
import com.product_service.dto.ProductResponseDTO;
import com.product_service.entity.Product;
import com.product_service.exception.ProductNotFoundException;
import com.product_service.repository.ProductRepository;
import com.product_service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository repository;

	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO productDTO) {
		logger.info("Saving Product to database: {}", productDTO);

		// Convert DTO to Entity
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(productDTO.getProductPrice());

		Product savedProduct = repository.save(product);
		logger.info("Product saved in DB ID: {}, Name: {}", savedProduct.getProductId(), savedProduct.getProductName());

		return mapToDTO(savedProduct);
	}

	
	// redis
	@Override
	@Cacheable(value = "products", key = "#id")
	public ProductResponseDTO getProductById(Long id) {
		logger.info("Fetching product from database with ID: {}", id);
		try {
			System.out.println("Product service called... waiting 6 seconds");

			Thread.sleep(6000); // 6 seconds delay

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

		return mapToDTO(product);
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		logger.info("Fetching products from database");
		List<Product> products = repository.findAll();
		logger.info("Fetched {} product from database", products.size());
		return products.stream().map(this::mapToDTO).toList();
	}

	private ProductResponseDTO mapToDTO(Product product) {
		if (product == null) {
			throw new RuntimeException("Product not found");
		}
		ProductResponseDTO dto = new ProductResponseDTO();

		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setProductPrice(product.getProductPrice());
		return dto;

	}

	@Override
	public List<ProductResponseDTO> getProductByName(String productName) {

		List<Product> products = repository.findByProductName(productName);

		if (products.isEmpty()) {
//			throw new ProductNotFoundException(null);
			throw new ProductNotFoundException("Product not found with name: " + productName);
		}
		return products.stream().map(this::mapToDTO).toList();

	}
}
