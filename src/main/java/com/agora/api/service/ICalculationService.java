package com.agora.api.service;

import java.math.BigDecimal;

import com.agora.api.model.Product;

public interface ICalculationService {
	
	public BigDecimal calculateTotalamountWithoutDisc(float inputItemQuantity,Product product);

}
