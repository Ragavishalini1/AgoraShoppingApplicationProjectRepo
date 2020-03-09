package com.agora.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agora.api.controller.dto.BillResponse;
import com.agora.api.controller.dto.ErrorResponse;
import com.agora.api.controller.dto.ProductItem;
import com.agora.api.data.AgoraInventoryRepository;
import com.agora.api.data.AgoraProductRepository;
import com.agora.api.model.Inventory;
import com.agora.api.model.Product;
import com.agora.api.service.AgoraShoppingService;
import com.agora.api.validation.AgoraBusinessValidation;

@Service
public class AgoraShoppingServiceImpl implements AgoraShoppingService {

	@Autowired
	private AgoraProductRepository agoraBusinessData;
	
	@Autowired
	private AgoraInventoryRepository agoraInventoryRepo;

	private String offerApplied;

	private double discountApplied;

	private List<Product> buyOneGetOneItemList;
	
	private Map<Integer,Product> productMap;
	
	private List<ErrorResponse> errorResponseList;
	
	private Logger log = Logger.getLogger("AgoraShoppingServiceImpl");

	@Override
	public List<Product> retrieveAllProducts() {
		
		log.info("Starting to retrieve all products");

		List<Product> listOfProducts = agoraBusinessData.findAll();

		return listOfProducts;
	}

	@Override
	public BillResponse processOrder(List<ProductItem> productItemList) {
		
		BigDecimal totalAmount = null;
		BigDecimal actualAmountWithoutDiscount = null;
		discountApplied = 0;
		offerApplied = null;
		buyOneGetOneItemList = new ArrayList<Product>();
		BillResponse billResponse = new BillResponse();

		try {

			log.info("Starting to Process Order");

			totalAmount = calculateTotalAmountWithoutOfferAndDiscount(productItemList);

			actualAmountWithoutDiscount = totalAmount;

			discountApplied = checkEligibilityAndReturnDiscountAmount();

			if (discountApplied != 0.0) {
				BigDecimal discountCalculated = (totalAmount.multiply(new BigDecimal(discountApplied)))
						.divide(new BigDecimal(100));
				totalAmount = totalAmount.subtract(discountCalculated);
			}

		} catch (Exception exception) {

			errorResponseList.add(new ErrorResponse("ERR500", exception.getMessage()));

		} finally {

			billResponse = constructBillResponse(totalAmount, actualAmountWithoutDiscount);
		}

		return billResponse;
	}

	private BigDecimal calculateTotalAmountWithoutOfferAndDiscount(List<ProductItem> productItemList) {
		BigDecimal totalAmount = new BigDecimal(0);
		productMap = new HashMap<Integer,Product>();
		errorResponseList = new ArrayList<ErrorResponse>();
		
		Iterator<ProductItem> productIterator = productItemList.iterator();
		
		while (productIterator.hasNext()) {

			ProductItem productItem = productIterator.next();
			float inputItemQuantity = (float) (Math.round(productItem.getQuantity() * 100.0) / 100.0);

			Optional<Product> product = agoraBusinessData.findById(productItem.getItemId());

			
			List<ErrorResponse> errorList = AgoraBusinessValidation.itemCodeAndQuantityValidation(product.isPresent(), productItem);
			if (errorList != null && errorList.size() > 0) {
				errorResponseList.addAll(errorList);
				
				continue;
			}

			productMap.put(productItem.getItemId(), product.get());

			BigDecimal quantityBasedCalculation = new BigDecimal(inputItemQuantity).multiply(new BigDecimal(1000))
					.divide(new BigDecimal(product.get().getProductSize()));

			totalAmount = product.get().getProductPrice().multiply(quantityBasedCalculation).add(totalAmount);
			
			updateInventory(inputItemQuantity, product);

		}

		return totalAmount;
	}

	private void updateInventory(float inputItemQuantity, Optional<Product> product) {
		
		Inventory inventoryItem = agoraInventoryRepo.findByProductId(product.get());
		float totalRemainingQuantity = (inventoryItem.getTotalRemainingQuantity()) - (inputItemQuantity*1000);
		inventoryItem.setTotalRemainingQuantity(totalRemainingQuantity);
		
		agoraInventoryRepo.save(inventoryItem);
	}

	private double checkEligibilityAndReturnDiscountAmount() {
		
		if (!productMap.isEmpty()) {

			Entry<Integer, Product> firstProductWithOffer = productMap.entrySet().stream()
					.filter(x -> x.getValue().getOffer().getOfferId() != NO_OFFER_CODE).findFirst().get();

			String offerId = firstProductWithOffer.getValue().getOffer().getOfferDescription();

			switch (offerId) {

			case B1G1: {
				offerApplied = B1G1;
				buyOneGetOneItemList.add(firstProductWithOffer.getValue());
				break;
			}
			case DISC5: {
				discountApplied = FIVE_PERCENT;
				offerApplied = DISC5;
				break;
			}
			case DISC10: {
				discountApplied = TEN_PERCENT;
				offerApplied = DISC10;
				break;
			}
			case FREEBOX: {
				offerApplied = FREEBOX;
				break;
			}
			default: {
				offerApplied = NOOFFER;
			}
			}

			if (productMap.size() > 5) {
				discountApplied = discountApplied + FIVE_PERCENT;
			} else if (productMap.size() > 10) {
				discountApplied = discountApplied + TEN_PERCENT;
			}
		}

		return discountApplied;
	}


	private BillResponse constructBillResponse(BigDecimal totalAmount,
			BigDecimal actualAmount) {
		
		BillResponse billResponse = new BillResponse();

		int noOfItems = buyOneGetOneItemList.isEmpty() ? productMap.size()
				: productMap.size() + buyOneGetOneItemList.size();
		billResponse.setNoOfItems(noOfItems);

		if (productMap.size() > 0) {
			String orderID = UUID.randomUUID().toString();
			billResponse.setOrderId(orderID);
		}

		billResponse.setOfferApplied(offerApplied);
		billResponse.setActualAmountToPay(actualAmount);
		billResponse.setDiscountApplied(new BigDecimal(discountApplied));
		billResponse.setAmountAfterDiscount(totalAmount);

		billResponse.setErrorResponse(errorResponseList);

		return billResponse;
	}

}
