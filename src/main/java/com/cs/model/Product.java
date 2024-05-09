package com.cs.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private int productID;
    private String productName;
    private String productDetails;
    private Date releasedDate;
    private int stock;
    private Integer warranty; // Use Integer for nullable fields
    private float price;
    private boolean delStatus;
    private int brandID;
    private int categoryID;
    private Integer discountID; // Use Integer for nullable fields
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}
	public Date getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Integer getWarranty() {
		return warranty;
	}
	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isDelStatus() {
		return delStatus;
	}
	public void setDelStatus(boolean delStatus) {
		this.delStatus = delStatus;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getDiscountID() {
		return discountID;
	}
	public void setDiscountID(Integer discountID) {
		this.discountID = discountID;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
 