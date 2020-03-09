package com.agora.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public class BillResponse {
	
	private String orderId;
	private int noOfItems;
	private String offerApplied;
	private BigDecimal actualAmountToPay;
	private BigDecimal discountApplied;
	private BigDecimal amountAfterDiscount;
	private List<ErrorResponse> errorResponse;
	
	public BillResponse() {
		
	}
	
	public BillResponse(String orderId, int noOfItems, String offerApplied, BigDecimal actualAmountToPay,
			BigDecimal discountApplied, BigDecimal amountAfterDiscount) {
		
		this.orderId = orderId;
		this.noOfItems = noOfItems;
		this.offerApplied = offerApplied;
		this.actualAmountToPay = actualAmountToPay;
		this.discountApplied = discountApplied;
		this.amountAfterDiscount = amountAfterDiscount;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getOfferApplied() {
		return offerApplied;
	}
	public void setOfferApplied(String offerApplied) {
		this.offerApplied = offerApplied;
	}
	public BigDecimal getActualAmountToPay() {
		return actualAmountToPay;
	}
	public void setActualAmountToPay(BigDecimal actualAmountToPay) {
		this.actualAmountToPay = actualAmountToPay;
	}
	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}
	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
	}
	public BigDecimal getAmountAfterDiscount() {
		return amountAfterDiscount;
	}
	public void setAmountAfterDiscount(BigDecimal amountAfterDiscount) {
		this.amountAfterDiscount = amountAfterDiscount;
	}
	
	public List<ErrorResponse> getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(List<ErrorResponse> errorResponse) {
		this.errorResponse = errorResponse;
	}

}
