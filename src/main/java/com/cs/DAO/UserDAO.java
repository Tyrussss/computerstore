package com.cs.DAO;

import com.cs.model.User;

public final class UserDAO {
	public static boolean newUser (User user) {
		try {
			return true;
			
		} catch (Exception e) {
		//TODO: handle exception
		return false;
	}
	}
}
