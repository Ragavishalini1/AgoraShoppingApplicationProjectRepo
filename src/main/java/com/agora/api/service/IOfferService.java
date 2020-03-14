package com.agora.api.service;

import java.util.Map;

import com.agora.api.model.Product;

public interface IOfferService {
	
	public static final String NOOFFER = "NOOFFER";
	public static final String FREEBOX = "FREEBOX";
	public static final String DISC10 = "DISC10";
	public static final String DISC5 = "DISC5";
	public static final String B1G1 = "B1G1";
	public static final String NO_OFFER_CODE = "OFF5";
	public static final int TEN_PERCENT = 10;
	public static final int FIVE_PERCENT = 5;
	
	public double checkOfferAndReturnDiscountApplied(Map<Integer, Product> productMap);
	
	public String getOfferApplied(Map<Integer, Product> productMap);

}
