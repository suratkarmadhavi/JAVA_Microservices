package com.payment_service.serviceImpl;

import com.payment_service.dto.PaymentRequestDTO;
import com.payment_service.dto.PaymentResponseDTO;
import com.payment_service.entity.Payment;
import com.payment_service.repository.PaymentRepository;
import com.payment_service.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
    private PaymentRepository paymentRepository;

   

    @Override
    public PaymentResponseDTO doPayment(PaymentRequestDTO paymentRequestDTO) {
    	
    	Payment payment = new Payment();
    	
    	payment.setOrderId(paymentRequestDTO.getOrderId());
    	payment.setAmount(paymentRequestDTO.getAmount());
    	payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
    	
    	if(paymentRequestDTO.getAmount() > 0) {
    		payment.setPaymentStatus("SUCCESS");
    	}else {
    		payment.setPaymentStatus("Failed");
    	}
    	
    	Payment savedPayment = paymentRepository.save(payment);
    	return mapToDTO(savedPayment);
    	   	
    }

	private PaymentResponseDTO mapToDTO(Payment savedPayment) {
        if(savedPayment == null) {
        	return null;
        }
        return new PaymentResponseDTO(savedPayment.getPaymentId(), savedPayment.getOrderId(),savedPayment.getAmount(),savedPayment.getPaymentStatus(),savedPayment.getPaymentMethod());
	}
}
