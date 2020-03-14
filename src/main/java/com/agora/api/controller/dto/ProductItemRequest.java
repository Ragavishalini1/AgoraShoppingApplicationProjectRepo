package com.agora.api.controller.dto;

public class ProductItemRequest {
	
	private int itemId;
	private float quantity;
	
	public ProductItemRequest() {
		
	}
	
	public ProductItemRequest(int itemId, float quantity) {
		
		this.itemId = itemId;
		this.quantity = quantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}
