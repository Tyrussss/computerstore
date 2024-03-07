package com.cs.model;


public class Category {
	
	private int idcategories;
	private String name_categories;
	private int status_categories;
	public int getIdcategories() {
		return idcategories;
	}
	public void setIdcategories(int idcategories) {
		this.idcategories = idcategories;
	}
	public String getName_categories() {
		return name_categories;
	}
	public void setName_categories(String name_categories) {
		this.name_categories = name_categories;
	}
	public int getStatus_categories() {
		return status_categories;
	}
	public void setStatus_categories(int status_categories) {
		this.status_categories = status_categories;
	}
	@Override
	public String toString() {
		return "Categories [idcategories=" + idcategories + ", name_categories=" + name_categories
				+ ", status_categories=" + status_categories + "]";
	}
	public Category(int idcategories, String name_categories, int status_categories) {
		super();
		this.idcategories = idcategories;
		this.name_categories = name_categories;
		this.status_categories = status_categories;
	}
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	
}
