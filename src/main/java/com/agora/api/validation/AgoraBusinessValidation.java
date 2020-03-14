package com.agora.api.validation;

import java.util.ArrayList;
import java.util.List;

import com.agora.api.controller.dto.ErrorResponse;
import com.agora.api.controller.dto.ProductItemRequest;

public class AgoraBusinessValidation {
	
	
	public static List<ErrorResponse> itemCodeAndQuantityValidation(boolean isProductPresent, ProductItemRequest productItem) {
		
		List<ErrorResponse> errorResponseList = new ArrayList<>();
		
		if(productItem.getQuantity() < 0.33) {
			ErrorResponse errorResponse = new ErrorResponse("ERR002","Quantity cannot be less than 0.33 for Item "+productItem.getItemId());
			errorResponseList.add(errorResponse);
		}
		
		if(!isProductPresent) {
			ErrorResponse errorResponse = new ErrorResponse("ERR001","Item " + productItem.getItemId() + " does not exist");
			errorResponseList.add(errorResponse);
		}
		
		return errorResponseList;
	}
	

}
