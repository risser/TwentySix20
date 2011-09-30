package com.twentysix20.kata.checkout;

public class PricingRule {
	private String sku;
	private int unitPrice;
	private int discountGroupSize;
	private int discountGroupPrice;

	public PricingRule(String sku, int unitPrice, int discountGroupSize, int discountPrice) {
		super();
		this.sku = sku;
		this.unitPrice = unitPrice;
		this.discountGroupSize = discountGroupSize;
		this.discountGroupPrice = discountPrice;
	}

	public PricingRule(String sku, int unitPrice) {
		this(sku,unitPrice,0,0);
	}
	
	public String getSKU() {
		return sku;
	}
	public void setSKU(String sku) {
		this.sku = sku;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getDiscountGroupSize() {
		return discountGroupSize;
	}
	public void setDiscountGroupSize(int discountGroupSize) {
		this.discountGroupSize = discountGroupSize;
	}
	public int getDiscountGroupPrice() {
		return discountGroupPrice;
	}
	public void setDiscountGroupPrice(int discountGroupPrice) {
		this.discountGroupPrice = discountGroupPrice;
	}
	public int getDiscountedPrice() {
		return !hasSpecialPricing() ? unitPrice : discountGroupPrice - (discountGroupSize - 1) * unitPrice; 
	}

	public boolean hasSpecialPricing() {
		return discountGroupSize != 0;
	}
}
