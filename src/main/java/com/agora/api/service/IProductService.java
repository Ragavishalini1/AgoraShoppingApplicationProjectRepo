package com.agora.api.service;

import java.util.List;

import com.agora.api.controller.dto.ProductItemRequest;
import com.agora.api.controller.dto.ProductItemResponse;

public interface IProductService {
	
	public ProductItemResponse processProducts(List<ProductItemRequest> productItemList);

}
