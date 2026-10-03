package com.payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment_service.dto.PaymentRequestDTO;
import com.payment_service.dto.PaymentResponseDTO;
import com.payment_service.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> doPayment(@RequestBody PaymentRequestDTO paymentRequestDto){
		PaymentResponseDTO paymentResponseDTO = paymentService.doPayment(paymentRequestDto);
		return new ResponseEntity<>(paymentResponseDTO,HttpStatus.CREATED);
		
	}

}
