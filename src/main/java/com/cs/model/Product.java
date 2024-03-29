package com.cs.model;

public class Product {
	private int ProductID;
	private String ProductName;
	private String ProductDetails;
	private int Stock;
	private int Warranty;
	private int Price;
	private int BrandID;
	private int CategoryID;
	//private Brand Brand;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductDetails() {
		return ProductDetails;
	}

	public void setProductDetails(String productDetails) {
		ProductDetails = productDetails;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public int getWarranty() {
		return Warranty;
	}

	public void setWarranty(int warranty) {
		Warranty = warranty;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getBrandID() {
		return BrandID;
	}

	public void setBrandID(int brandID) {
		BrandID = brandID;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	
	
	
}
 