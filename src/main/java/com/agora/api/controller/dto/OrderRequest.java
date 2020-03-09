package com.agora.api.controller.dto;

import java.util.List;


public class OrderRequest {
	
	private List<ProductItem> selectedItemList;

	public List<ProductItem> getSelectedItemList() {
		return selectedItemList;
	}

	public void setSelectedItemList(List<ProductItem> selectedItemList) {
		this.selectedItemList = selectedItemList;
	}

}
