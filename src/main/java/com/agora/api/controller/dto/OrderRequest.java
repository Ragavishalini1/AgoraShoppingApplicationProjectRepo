package com.agora.api.controller.dto;

import java.util.List;


public class OrderRequest {
	
	private List<ProductItemRequest> selectedItemList;

	public List<ProductItemRequest> getSelectedItemList() {
		return selectedItemList;
	}

	public void setSelectedItemList(List<ProductItemRequest> selectedItemList) {
		this.selectedItemList = selectedItemList;
	}

}
