package com.agora.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.agora.api.model.Product;

public class ProductItemResponse {
	
	private Map<Integer,Product> products;
	private List<ErrorResponse> errorList;
	private BigDecimal amountAfterDiscount;
	private BigDecimal actualAmountToPay;
	private BigDecimal discountApplied;
	private String offerApplied;
	private int totalNumberOfItems;
	
	public int getTotalNumberOfItems() {
		return totalNumberOfItems;
	}
	public void setTotalNumberOfItems(int totalNumberOfItems) {
		this.totalNumberOfItems = totalNumberOfItems;
	}
	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}
	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
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
	
	public List<ErrorResponse> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<ErrorResponse> errorList) {
		this.errorList = errorList;
	}
	public BigDecimal getTotalAmount() {
		return amountAfterDiscount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.amountAfterDiscount = totalAmount;
	}
	
	public Map<Integer, Product> getProducts() {
		return products;
	}
	public void setProducts(Map<Integer, Product> products) {
		this.products = products;
	}

}
