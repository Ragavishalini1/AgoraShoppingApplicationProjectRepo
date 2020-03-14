package com.agora.api.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.agora.api.model.Product;
import com.agora.api.service.ICalculationService;

@Service
public class CalculationServiceImpl implements ICalculationService{

	@Override
	public BigDecimal calculateTotalamountWithoutDisc(float inputItemQuantity, Product product) {
		
		BigDecimal totalAmount = new BigDecimal(0);
		
		BigDecimal quantityBasedCalculation = new BigDecimal(inputItemQuantity).multiply(new BigDecimal(1000))
				.divide(new BigDecimal(product.getProductSize()));

		totalAmount = product.getProductPrice().multiply(quantityBasedCalculation).add(totalAmount);
		
		
		return totalAmount;
	}

}
