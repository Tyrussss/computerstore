package com.cs.model;

public class Category {
	private int CategoryID;
	private String CategoryName;
	private boolean DelStatus;
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public boolean isDelStatus() {
		return DelStatus;
	}
	public void setDelStatus(boolean delStatus) {
		DelStatus = delStatus;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
