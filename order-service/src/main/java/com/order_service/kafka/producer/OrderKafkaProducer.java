package com.order_service.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.events.ProductRequestEvent;

@Service 
public class OrderKafkaProducer {
	
	 private static final String TOPIC = "product-request-topic";

	    @Autowired
	    private KafkaTemplate<String, ProductRequestEvent> kafkaTemplate;

	    public void sendProductRequest(ProductRequestEvent event) {

	        kafkaTemplate.send(TOPIC, event);

	        System.out.println("Message sent to Kafka for productId: " + event.getProductId());
	    }

}
