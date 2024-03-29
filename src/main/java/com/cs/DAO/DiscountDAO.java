package com.cs.DAO;

import com.cs.model.Discount;

public final class DiscountDAO {
	public static boolean newDiscount (Discount code) {
		try {
			return true;
					
		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}
}
