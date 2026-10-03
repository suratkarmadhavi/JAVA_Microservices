package com.product_service.dto;

import java.io.Serializable;

public class ProductResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Long productId;
    private Long orderId;
    private String productName;
    private Double productPrice;

    public ProductResponseDTO() {}

	@Override
	public String toString() {
		return "ProductResponseDTO [productId=" + productId + ", orderId=" + orderId + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}

	public ProductResponseDTO(Long productId, Long orderId, String productName, Double productPrice) {
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

  

 
}
