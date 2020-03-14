package com.agora.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agora.api.common.CostCalculator;
import com.agora.api.controller.dto.ErrorResponse;
import com.agora.api.controller.dto.ProductItemRequest;
import com.agora.api.controller.dto.ProductItemResponse;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Product;
import com.agora.api.service.IInventoryService;
import com.agora.api.service.IOfferService;
import com.agora.api.service.IProductService;
import com.agora.api.validation.AgoraBusinessValidation;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	public IInventoryService inventoryService;
	
	@Autowired
	public IOfferService offerService;
	
	@Autowired
	public AgoraProductRepository agoraProductRepo;

	@Override
	public ProductItemResponse processProducts(List<ProductItemRequest> productItemList) {
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal actualAmountwithoutDiscount = new BigDecimal(0);
		Map<Integer,Product> productMap = new HashMap<Integer,Product>();
		List<ErrorResponse> errorResponseList = new ArrayList<ErrorResponse>();
		
		double discountApplied  = 0;
		
		Iterator<ProductItemRequest> productIterator = productItemList.iterator();
		
		while (productIterator.hasNext()) {
			
			ProductItemRequest productItem = productIterator.next();

			Optional<Product> product = agoraProductRepo.findById(productItem.getItemId());
			
			float inputItemQuantity = (float) (Math.round(productItem.getQuantity() * 100.0) / 100.0);
			
			List<ErrorResponse> errorList = AgoraBusinessValidation.itemCodeAndQuantityValidation(product.isPresent(), productItem);
			if (errorList != null && errorList.size() > 0) {
				errorResponseList.addAll(errorList);
				
				continue;
			}

			productMap.put(productItem.getItemId(), product.get());
			
			
			totalAmount =  totalAmount.add(CostCalculator.calculateTotalamount(inputItemQuantity, product.get()));
			
			
			inventoryService.updateInventory(inputItemQuantity, product);

		}
		
		actualAmountwithoutDiscount = totalAmount;
		
		discountApplied = offerService.checkOfferAndReturnDiscountApplied(productMap);
		
		
		if (discountApplied != 0.0) {
			totalAmount = CostCalculator.calculateAmountApplyingDiscount(totalAmount, discountApplied);
		}
		
		ProductItemResponse productResponse = constructProductResponse(totalAmount, actualAmountwithoutDiscount,
				productMap, errorResponseList, discountApplied);
		
		
		return productResponse;
	}

	private ProductItemResponse constructProductResponse(BigDecimal totalAmount, BigDecimal actualAmountwithoutDiscount,
			Map<Integer, Product> productMap, List<ErrorResponse> errorResponseList, double discountApplied) {
		ProductItemResponse productResponse = new ProductItemResponse();
		
		productResponse.setErrorList(errorResponseList);
		productResponse.setProducts(productMap);
		productResponse.setActualAmountToPay(actualAmountwithoutDiscount);
		
		
		productResponse.setDiscountApplied(new BigDecimal(discountApplied));
		productResponse.setTotalAmount(totalAmount);
		
		int noOfItems = productMap.size();
		
		String offerApplied = offerService.getOfferApplied(productMap);		
		if(offerApplied != null && offerApplied.equalsIgnoreCase("B1G1")) {
			noOfItems++;
		}
		
		productResponse.setTotalNumberOfItems(noOfItems);
		productResponse.setOfferApplied(offerApplied);
		return productResponse;
	}

}
