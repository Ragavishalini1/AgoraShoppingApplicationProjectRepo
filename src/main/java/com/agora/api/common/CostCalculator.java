package com.agora.api.common;

import java.math.BigDecimal;

import com.agora.api.model.Product;

public class CostCalculator{

	public static BigDecimal calculateTotalamount(float inputItemQuantity, Product product) {
		
		BigDecimal totalAmount = new BigDecimal(0);
		
		BigDecimal quantityBasedCalculation = new BigDecimal(inputItemQuantity).multiply(new BigDecimal(1000))
				.divide(new BigDecimal(product.getProductSize()));

		totalAmount = product.getProductPrice().multiply(quantityBasedCalculation).add(totalAmount);

		return totalAmount;
	}

	public static BigDecimal calculateAmountApplyingDiscount(BigDecimal totalAmount, double discountPercentage) {

		BigDecimal discountCalculated = (totalAmount.multiply(new BigDecimal(discountPercentage)))
				.divide(new BigDecimal(100));
		totalAmount = totalAmount.subtract(discountCalculated);

		return totalAmount;

	}

}
