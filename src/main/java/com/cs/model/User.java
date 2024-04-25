package com.cs.model;

public class User {
	private int UserID;
	private String Username;
	private String Password;
	private String Email;
	private String FullName;
	private String Phone;
	private String Address;
	private int Role;
	private boolean Newsletter;
	private String Avatar;
	
	
	public int getUserID() {
		return UserID;
	}


	public void setUserID(int userID) {
		UserID = userID;
	}


	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getFullName() {
		return FullName;
	}


	public void setFullName(String fullName) {
		FullName = fullName;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public int getRole() {
		return Role;
	}


	public void setRole(int role) {
		Role = role;
	}


	public boolean getNewsletter() {
		return Newsletter;
	}


	public void setNewsletter(boolean newsletter) {
		Newsletter = newsletter;
	}


	public String getAvatar() {
		return Avatar;
	}


	public void setAvatar(String avatar) {
		Avatar = avatar;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
