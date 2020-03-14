package com.agora.api.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.agora.api.model.Product;
import com.agora.api.service.IOfferService;

@Service
public class OfferServiceImpl implements IOfferService{
	
	@Override
	public double checkOfferAndReturnDiscountApplied(Map<Integer, Product> productMap) {
		
		double discountApplied = 0;
		
		if (!productMap.isEmpty()) {

			Entry<Integer, Product> firstProductWithOffer = productMap.entrySet().stream()
					.filter(x -> x.getValue().getOffer().getOfferId() != NO_OFFER_CODE).findFirst().get();

			String offerId = firstProductWithOffer.getValue().getOffer().getOfferDescription();

			switch (offerId) {

			case B1G1: {
				// buyOneGetOneItemList.add(firstProductWithOffer.getValue());
				break;
			}
			case DISC5: {
				discountApplied = FIVE_PERCENT;
				break;
			}
			case DISC10: {
				discountApplied = TEN_PERCENT;
				break;
			}
			default: {
				discountApplied = 0;
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
	
	@Override
	public String getOfferApplied(Map<Integer, Product> productMap) {
		String offerId = NOOFFER;
		
		if (!productMap.isEmpty()) {

			Entry<Integer, Product> firstProductWithOffer = productMap.entrySet().stream()
					.filter(x -> x.getValue().getOffer().getOfferId() != NO_OFFER_CODE).findFirst().get();

			offerId = firstProductWithOffer.getValue().getOffer().getOfferDescription();
			
		}
		
		return offerId;
	}
}
