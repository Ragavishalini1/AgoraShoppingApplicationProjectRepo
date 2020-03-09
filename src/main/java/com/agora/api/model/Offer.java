package com.agora.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Offer {
	
	@Id
	private String offerId;
	private String offerDescription;
	
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

}
