package com.cs.DAO;

import com.cs.model.Brand;

public class BrandDAO {
	public static boolean newBrand(Brand Item) {
		try {
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
