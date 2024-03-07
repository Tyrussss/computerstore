package com.cs.DAO;

import com.cs.model.Product;

public final class ProductDAO {
	public static boolean newProduct(Product Item) {
		try {
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
