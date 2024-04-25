package com.cs.model;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
	private int OrderID;
	private Boolean PaymentStatus;
	private int UserID;
	private String payment;
	private Date Created_Date;

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public Boolean getPaymentStatus() {
		return PaymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		PaymentStatus = paymentStatus;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getCreated_Date() {
		return Created_Date;
	}
	public void setCreated_Date(Date Created_Date) {
		this.Created_Date = Created_Date;
	}
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	

	public Order(int orderID, Boolean paymentStatus, int userID, String payment, Date created_Date) {
		super();
		OrderID = orderID;
		PaymentStatus = paymentStatus;
		UserID = userID;
		this.payment = payment;
		Created_Date = created_Date;
	}
	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order{" +
				"OrderID=" + OrderID +
				", PaymentStatus=" + PaymentStatus +
				", UserID=" + UserID +
				", Created_Date=" + Created_Date +
				'}';
	}
}
 