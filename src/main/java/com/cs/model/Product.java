package com.cs.model;

import java.sql.Date;

public class Product {
	private int idproduct;
	private String product_name;
	private int product_unit;
	private int product_price;
	private int product_sold;
	private int product_stock;
	private Date product_exp;
	private int idcategories;
	public int getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(int product_unit) {
		this.product_unit = product_unit;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_sold() {
		return product_sold;
	}
	public void setProduct_sold(int product_sold) {
		this.product_sold = product_sold;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public Date getProduct_exp() {
		return product_exp;
	}
	public void setProduct_exp(Date product_exp) {
		this.product_exp = product_exp;
	}
	public int getIdcategories() {
		return idcategories;
	}
	public void setIdcategories(int idcategories) {
		this.idcategories = idcategories;
	}
	public Product() {
		super();
	}
	
	
}
