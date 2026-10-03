package com.order_service.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.events.ProductResponseEvent;
import com.order_service.entity.Order;
import com.order_service.repository.OrderRepository;

@Service
public class OrderKafkaConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "product-response-topic", groupId = "order-group")
    public void consume(ProductResponseEvent event) {

        System.out.println("Received product response from Kafka");

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double totalPrice = event.getProductPrice() * order.getQuantity();

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
    }
}