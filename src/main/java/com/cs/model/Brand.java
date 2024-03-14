package com.cs.model;

public class Brand {
	private int BrandID;
	private String BrandName;
	private boolean DelStatus;
	public int getBrandID() {
		return BrandID;
	}
	public void setBrandID(int brandID) {
		BrandID = brandID;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	public boolean isDelStatus() {
		return DelStatus;
	}
	public void setDelStatus(boolean delStatus) {
		DelStatus = delStatus;
	}
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
