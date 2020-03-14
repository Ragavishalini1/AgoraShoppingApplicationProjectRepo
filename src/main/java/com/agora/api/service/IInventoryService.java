package com.agora.api.service;

import java.util.Optional;

import com.agora.api.model.Product;

public interface IInventoryService {
	
	public void updateInventory(float inputItemQuantity, Optional<Product> product);

}
