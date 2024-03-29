package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Review;

@Repository
public class ReviewRepository {
	@Autowired
	JdbcTemplate db;
	
	class ReviewRowMapper implements RowMapper<Review>{
		@Override
		public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			Review review = new Review();
			review.setReviewID(rs.getInt("ReviewID"));
			review.setUserID(rs.getInt("UserID"));
			review.setProductID(rs.getInt("ProductID"));
			review.setReviewContent(rs.getString("ReviewContent"));
			review.setReply(rs.getString("Reply"));
			return review;
		}
	}
	/***
	 * 
	 * @return select table brand
	 */
	public List<Review> findAll(){
		try {
			return db.query("select * from Review", new ReviewRowMapper());
		}catch (Exception ec) {
			ec.printStackTrace();
			throw new RuntimeException("Error!!");
		}
	}
	
	/***
	 * 
	 * @return select 1
	 */	
	public Review findByID(int id) {
		return db.queryForObject ("Select * from Review where ReviewID = ?", new ReviewRowMapper(),
				new Object[] {id});
	}
}
