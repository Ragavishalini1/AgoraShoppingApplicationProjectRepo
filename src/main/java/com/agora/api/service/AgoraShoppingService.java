package com.agora.api.service;

import java.util.List;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.ProductItem;
import com.agora.api.model.Product;

public interface AgoraShoppingService {
	
	public static final String NO_OFFER_CODE = "OFF5";
	public static final int TEN_PERCENT = 10;
	public static final int FIVE_PERCENT = 5;
	public static final String ERR001 = "ERR001";
	public static final String ERR002 = "ERR002";
	public static final String NOOFFER = "NOOFFER";
	public static final String FREEBOX = "FREEBOX";
	public static final String DISC10 = "DISC10";
	public static final String DISC5 = "DISC5";
	public static final String B1G1 = "B1G1";

	public List<Product> retrieveAllProducts();
	
	public BillResponse processOrder(List<ProductItem> productItemList);
	
}
