package com.agora.api.controller.dto;

import java.util.List;

public class BillResponse {
	
	private String orderId;
	private List<ErrorResponse> errorResponse;
	private ProductItemResponse productItemResponse;
	
	public BillResponse() {
	}
	
	public BillResponse(String orderId) {
		this.orderId = orderId;
	}
	
	public ProductItemResponse getProductItemResponse() {
		return productItemResponse;
	}

	public void setProductItemResponse(ProductItemResponse productItemResponse) {
		this.productItemResponse = productItemResponse;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public List<ErrorResponse> getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(List<ErrorResponse> errorResponse) {
		this.errorResponse = errorResponse;
	}

}
