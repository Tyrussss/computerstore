package com.cs.model;

public class Cart {
	private int CartID;
	private int ProductID;
	private String ProductName;
	private int UserID;
	private int Quantity;
	private float Price;
	private float TotalPrice;

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getCartID() {
		return CartID;
	}

	public void setCartID(int cartID) {
		CartID = cartID;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public float getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		TotalPrice = totalPrice;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cart{" +
				"CartID=" + CartID +
				", ProductID=" + ProductID +
				", ProductName='" + ProductName + '\'' +
				", UserID=" + UserID +
				", Quantity=" + Quantity +
				", Price=" + Price +
				", TotalPrice=" + TotalPrice +
				'}';
	}
}
 