package com.cs.DAO;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.cs.model.Category;

public final class CategoryDAO {
	public static boolean newCategory(Category Item) {
		try {
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
}
