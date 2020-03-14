package com.agora.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.ErrorResponse;
import com.agora.api.controller.dto.OrderRequest;
import com.agora.api.controller.dto.ProductItemRequest;
import com.agora.api.controller.dto.ProductItemResponse;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Product;
import com.agora.api.service.IAgoraShoppingService;
import com.agora.api.service.IProductService;

/**
 * 
 * @author ragavishalini
 * 
 * This Service class holds the business logic for the processing of order
 *
 */
@Service
public class AgoraShoppingServiceImpl implements IAgoraShoppingService {

	@Autowired
	private AgoraProductRepository agoraBusinessData;
	
	@Autowired
	private IProductService productService;

	private List<ErrorResponse> errorResponseList;
	
	private Logger log = Logger.getLogger("AgoraShoppingServiceImpl");
	
	private ProductItemResponse productItemResponse;

	/**
	 * To retrieve all the available products
	 */
	@Override
	public List<Product> retrieveAllProducts() {
		
		log.info("Starting to retrieve all products");

		List<Product> listOfProducts = agoraBusinessData.findAll();

		return listOfProducts;
	}

	/**
	 * Processes the order based on the Item Code and Quantity
	 */
	@Override
	public BillResponse processOrder(OrderRequest orderRequest) {
		
		BillResponse billResponse = new BillResponse();
		errorResponseList = new ArrayList<ErrorResponse>();

		try {

			log.info("Starting to Process Order");
			
			List<ProductItemRequest> productItemList = orderRequest.getSelectedItemList();
			
			productItemResponse = productService.processProducts(productItemList);
			
			log.info("End of processing order");
			
		} catch (Exception exception) {

			errorResponseList.add(new ErrorResponse("ERR500", exception.getMessage()));

		} finally {

			billResponse = constructBillResponse();
		}

		return billResponse;
	}
	

	/**
	 * constructs the bill response output to display 
	 * 
	 * @param totalAmount
	 * @param actualAmount
	 * @return
	 */
	
	private BillResponse constructBillResponse() {
		
		BillResponse billResponse = new BillResponse();
		
		Map<Integer,Product> productMap = productItemResponse.getProducts();
		
		if (productMap.size() > 0) {
			String orderID = UUID.randomUUID().toString();
			billResponse.setOrderId(orderID);
		}

		billResponse.setProductItemResponse(productItemResponse);

		errorResponseList.addAll(productItemResponse.getErrorList());
		billResponse.setErrorResponse(errorResponseList);

		return billResponse;
	}

	@Override
	public Product retrieveProductByProductId(int productId) {
		return agoraBusinessData.findByProductId(productId);
	}

}
