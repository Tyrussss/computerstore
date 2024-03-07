package com.cs.repository;


import org.springframework.stereotype.Repository;

import com.cs.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



@Repository
public class CategoryRepository {
	@Autowired
	JdbcTemplate db;

	class CategoryRowMapper implements RowMapper<Category> {
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category item = new Category();
			item.setIdcategories(rs.getInt("idcategories"));
			item.setName_categories(rs.getString("name_categories"));
			item.setStatus_categories(rs.getInt("status_categories"));
			return item;
		}
	}

	/***
	 * 
	 * @return select table category
	 */
	public List<Category> findAll() {
		try {
			return db.query("select * from categories", new CategoryRowMapper());
		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
	

	public Category findById(int id) {
		return db.queryForObject("select * from Categories where idcategories=?", new CategoryRowMapper(),
				new Object[] { id });
	}

	public int deleteById(int id) {
		return db.update("delete from Categories where idcategories=?", new Object[] { id });
	}

	public int insert(Category Category) {
		return db.update("insert into Categories ( naem_categories, status_categories) " + "values( ?, ?)",
				new Object[] { Category.getName_categories(), Category.getStatus_categories() });
	}

	public int update(Category Category) {
		return db.update("update Categories " + " set category_name = ?, category_active = ?" + " where idcategories = ?",
				new Object[] { Category.getName_categories(), Category.getStatus_categories(), Category.getIdcategories() });
	}
}
