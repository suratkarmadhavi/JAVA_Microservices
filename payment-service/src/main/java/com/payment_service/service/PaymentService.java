package com.payment_service.service;

import org.springframework.stereotype.Service;

import com.payment_service.dto.PaymentRequestDTO;
import com.payment_service.dto.PaymentResponseDTO;


@Service
public interface PaymentService {

  

	PaymentResponseDTO doPayment(PaymentRequestDTO paymentRequestDto);
}
