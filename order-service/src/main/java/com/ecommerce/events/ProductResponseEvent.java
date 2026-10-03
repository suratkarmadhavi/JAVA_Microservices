package com.ecommerce.events;

public class ProductResponseEvent {
	private Long productId;
	private Long orderId;
	private String productName;
	private Double productPrice;

	public ProductResponseEvent() {
	}

	public ProductResponseEvent(Long productId, Long orderId, String productName, Double productPrice) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
