package com.cs.model;

public class Discount {
	private int DiscountID;
	private String Code;
	private String Type;
	private String StartDate;
	private String EndDate;
	private int Percent;
	
	
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDiscountID() {
		return DiscountID;
	}
	public void setDiscountID(int discountID) {
		DiscountID = discountID;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public int getPercent() {
		return Percent;
	}
	public void setPercent(int percent) {
		Percent = percent;
	}
	
	
}
