package com.agora.api.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	private int productId;
	
	private String productName;
	
	private float productSize;
	
	private String units;
	
	private BigDecimal productPrice;
	
	@ManyToOne
	@JoinColumn(name = "offerId")
	private Offer offer;
	
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductSize(float productSize) {
		this.productSize = productSize;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	public int getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public float getProductSize() {
		return productSize;
	}
	public String getUnits() {
		return units;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}

}
