package com.agora.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agora.api.model.Inventory;
import com.agora.api.model.Product;

@Repository
public interface AgoraInventoryRepository extends JpaRepository<Inventory,Integer> {
	
	
	public Inventory findByProductId(@Param("productId") Product productId);
	
	public Inventory findByProductIdProductName(String productName);

}
