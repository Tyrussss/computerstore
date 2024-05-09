package com.cs.model;

public class ProductDTO {
	 	private int productID;
	    private String productName;
	    private String productDetails;
	    private int stock;
	    private int warranty;
	    private float price;
	    private String brandName;
	    private String categoryName;
	    // Getters and setters
		public int getProductID() {
			return productID;
		}
		public void setProductID(int productID) {
			this.productID = productID;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getProductDetails() {
			return productDetails;
		}
		public void setProductDetails(String productDetails) {
			this.productDetails = productDetails;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public int getWarranty() {
			return warranty;
		}
		public void setWarranty(int warranty) {
			this.warranty = warranty;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public ProductDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
