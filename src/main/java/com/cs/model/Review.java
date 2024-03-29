package com.cs.model;

public class Review {
	private int ReviewID;
	private int UserID;
	private int ProductID;
	private String ReviewContent;
	private String Reply;
	
	
	public int getReviewID() {
		return ReviewID;
	}
	public void setReviewID(int reviewID) {
		ReviewID = reviewID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getReviewContent() {
		return ReviewContent;
	}
	public void setReviewContent(String reviewContent) {
		ReviewContent = reviewContent;
	}
	public String getReply() {
		return Reply;
	}
	public void setReply(String reply) {
		Reply = reply;
	}
	
	public Review() {
		super();
		//TODO Auto-generated constructor stub
	}
}
