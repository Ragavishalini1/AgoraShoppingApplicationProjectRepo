package com.agora.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Inventory {

	@Id
	private int inventoryId;

	@OneToOne
	@JoinColumn(name = "productId")
	private Product productId;
	private float prescribedQuantity;
	private int noOfUnits;
	private float totalRemainingQuantity;

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public float getPrescribedQuantity() {
		return prescribedQuantity;
	}

	public void setPrescribedQuantity(float prescribedQuantity) {
		this.prescribedQuantity = prescribedQuantity;
	}

	public int getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public float getTotalRemainingQuantity() {
		return totalRemainingQuantity;
	}

	public void setTotalRemainingQuantity(float totalRemainingQuantity) {
		this.totalRemainingQuantity = totalRemainingQuantity;
	}

}
