package com.cs.model;

public class OrderDetail {
	private int OrderID;
	private int ProductID;
	private int Quantity;
	private String SubTotal;
	private String Total;
	private float Price;
	private int DiscountID;
	private boolean PaymentStatus;
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(String subTotal) {
		SubTotal = subTotal;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public int getDiscountID() {
		return DiscountID;
	}
	public void setDiscountID(int discountID) {
		DiscountID = discountID;
	}
	public boolean isPaymentStatus() {
		return PaymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		PaymentStatus = paymentStatus;
	}
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
