package com.cs.DAO;

import com.cs.model.ProductInfo;

public class ProductInfoDAO {
	public static boolean newProductInfoDAO (ProductInfo Info) {
		try {
			return true;
		}catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}
}
