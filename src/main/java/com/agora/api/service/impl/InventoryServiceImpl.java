package com.agora.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agora.api.data.AgoraInventoryRepository;
import com.agora.api.model.Inventory;
import com.agora.api.model.Product;
import com.agora.api.service.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService{
	
	@Autowired
	private AgoraInventoryRepository agoraInventoryRepo;

	@Override
	public void updateInventory(float inputItemQuantity, Optional<Product> product) {

		Inventory inventoryItem = agoraInventoryRepo.findByProductId(product.get());
		float totalRemainingQuantity = (inventoryItem.getTotalRemainingQuantity()) - (inputItemQuantity*1000);
		inventoryItem.setTotalRemainingQuantity(totalRemainingQuantity);
		
		agoraInventoryRepo.save(inventoryItem);
	}

}
