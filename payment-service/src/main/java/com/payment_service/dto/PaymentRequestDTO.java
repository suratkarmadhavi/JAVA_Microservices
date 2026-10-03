package com.payment_service.dto;

import jakarta.validation.constraints.NotBlank;

public class PaymentRequestDTO {
	
	@NotBlank(message = "Order ID is required")
	private Long orderId;
	
	
	private Double amount;
	
	private String paymentStatus;
	
	private String paymentMethod;


	public PaymentRequestDTO() {
		
	}

	@Override
	public String toString() {
		return "PaymentRequestDTO [orderId=" + orderId + ", amount=" + amount + ", paymentStatus=" + paymentStatus
				+ ", paymentMethod=" + paymentMethod + "]";
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
