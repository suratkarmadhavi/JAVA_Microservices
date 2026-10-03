package com.payment_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_id", nullable=false)
    private Long orderId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_status", nullable=false)
    private String paymentStatus; // SUCCESS, FAILED

    @Column(name = "payment_method", nullable=false)
    private String paymentMethod; // UPI, CARD, COD
    
    

	public Payment() {
	
	}



	public Payment(Long paymentId, Long orderId, Double amount, String paymentStatus, String paymentMethod) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}



	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderId=" + orderId + ", amount=" + amount + ", paymentStatus="
				+ paymentStatus + ", paymentMethod=" + paymentMethod + "]";
	}



	public Long getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
