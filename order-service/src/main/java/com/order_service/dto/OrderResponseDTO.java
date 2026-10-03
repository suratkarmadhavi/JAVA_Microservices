package com.order_service.dto;

public class OrderResponseDTO {

	private Long id;

	private Long productId;

	private Integer quantity;

	private Double totalPrice;

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(Long id, Long productId, Integer quantity, Double totalPrice) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
