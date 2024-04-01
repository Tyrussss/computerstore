package com.cs.model;

public class ProductInfo {
	private int ProductInfoID;
	private String Name;
	private String Content;
	private int ProductID;
	
	public int getProductInfoID() {
		return ProductInfoID;
	}
	public void setProductInfoID(int productInfoID) {
		ProductInfoID = productInfoID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	
	public ProductInfo() {
		super();
		//TODO Auto-generated constructor stub
	}
}
