package com.cs.DAO;

import com.cs.model.ProductImage;

public class ProductImageDAO {
	public static boolean newProductImage(ProductImage Image) {
		try {
			return true;
		}catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}
}
