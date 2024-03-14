package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Category;

@Repository
public class CategoryRepository {
	@Autowired
	JdbcTemplate db;

	class CategoryRowMapper implements RowMapper<Category> {
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category item = new Category();
			item.setCategoryID(rs.getInt("CategoryID"));
			item.setCategoryName(rs.getString("CategoryName"));
			item.setDelStatus(rs.getBoolean("DelStatus"));
			return item;
		}
	}
	
	/***
	 * 
	 * @return select table category
	 */
	public List<Category> findAll() {
		try {
			return db.query("select * from Category", new CategoryRowMapper());
		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
}
