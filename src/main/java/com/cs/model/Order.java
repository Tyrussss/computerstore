package com.cs.model;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
	private int OrderID;
	private Boolean PaymentStatus;
	private int UserID;
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
 