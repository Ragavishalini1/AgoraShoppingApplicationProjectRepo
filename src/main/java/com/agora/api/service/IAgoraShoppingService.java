package com.agora.api.service;

import java.util.List;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.model.Product;

public interface IAgoraShoppingService {
	
	public static final String ERR001 = "ERR001";
	public static final String ERR002 = "ERR002";
	

	public List<Product> retrieveAllProducts();
	
	public Product retrieveProductByProductId(int productId);
	
	public BillResponse processOrder(OrderRequest orderRequest);
	
}
