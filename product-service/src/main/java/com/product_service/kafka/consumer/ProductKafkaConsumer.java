package com.product_service.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.events.ProductRequestEvent;
import com.ecommerce.events.ProductResponseEvent;
import com.product_service.entity.Product;
import com.product_service.exception.ProductNotFoundException;
import com.product_service.repository.ProductRepository;

@Service
public class ProductKafkaConsumer {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private KafkaTemplate<String, ProductResponseEvent> kafkaTemplate;

	@KafkaListener(topics = "product-request-topic", groupId = "product-group")
	public void consume(ProductRequestEvent event) {

		System.out.println(event);
		Product product = productRepository.findById(event.getProductId())
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + event.getProductId()));

		ProductResponseEvent response = new ProductResponseEvent(event.getOrderId(), product.getProductId(),
				product.getProductName(), product.getProductPrice());

		kafkaTemplate.send("product-response-topic", response);

		System.out.println(response);
		System.out.println("Product Response sends back to kafka");

	}

}
