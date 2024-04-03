package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
			return db.query("select * from Category where DelStatus = 1", new CategoryRowMapper());
		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
	
	/***
	 * 
	 * @return select 1 
	 */
		
	public Category findByID(int id) {
		return db.queryForObject("select * from Category where CategoryID=?", new CategoryRowMapper(),
				new Object[] { id });
	}
	
//	public int deleteById(int id) {
//		return db.update("delete from Category where CategoryID=?", new Object[] { id });
//	}

	
	
	public int insert(Category Category) {
		return db.update("insert into Category ( CategoryName, DelStatus) " + "values( ?, ?)",
				new Object[] { Category.getCategoryName(), 1 });
	}

	public int update(Category Category) {
		return db.update("update Category " + " set CategoryName = ?, DelStatus = ?" + " where CategoryID = ?",
				new Object[] { Category.getCategoryName(), 1, Category.getCategoryID() });
	}
	public int deleteById(Category Category, int id) {
		return db.update("update Category " + " set CategoryName = ?, DelStatus = ?" + " where CategoryID = ?",
				new Object[] { Category.getCategoryName(), 0, id });
	}	
	
}
