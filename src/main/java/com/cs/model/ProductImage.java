package com.cs.model;

public class ProductImage {
	private int ProductImageID;
	private String Image;
	private Boolean Main;
	private int ProductID;
	
	public ProductImage() {
		super();
		//TODO Auto-generated constructor sub
	}
	public int getProductImageID() {
		return ProductImageID;
	}
	public void setProductImageID(int productImageID) {
		ProductImageID = productImageID;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Boolean getMain() {
		return Main;
	}
	public void setMain(Boolean main) {
		Main = main;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	
	
}

