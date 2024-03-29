package com.cs.DAO;

import com.cs.model.Review;

public class ReviewDAO {
	public static boolean newReview (Review review) {
		try {
			return true;
		}catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}
}
