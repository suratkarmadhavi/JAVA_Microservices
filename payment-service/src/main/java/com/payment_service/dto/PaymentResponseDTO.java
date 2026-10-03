package com.payment_service.dto;

public class PaymentResponseDTO {

	private Long paymentId;
	
	private Long orderId;
	
	private Double amount;
	
	private String paymentStatus;
	
	private String paymentMethod;

	public PaymentResponseDTO() {
		
	}
	
	

	public PaymentResponseDTO(Long paymentId, Long orderId, Double amount, String paymentStatus, String paymentMethod) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}



	public Long getPaymentId() {
		return paymentId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	
	
}
