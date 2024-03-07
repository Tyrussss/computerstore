package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cs.model.Category;
import com.cs.model.Product;
import com.cs.repository.CategoryRepository.CategoryRowMapper;

public class ProductRepository {
	@Autowired
	JdbcTemplate db;

	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product item = new Product();
			item.setIdproduct(rs.getInt("idproduct"));
			item.setProduct_name(rs.getString("product_name"));
			item.setProduct_unit(rs.getInt("product_unit"));
			item.setProduct_price(rs.getInt("product_price"));
			item.setProduct_sold(rs.getInt("product_sold"));
			item.setProduct_stock(rs.getInt("product_stock"));
			item.setProduct_exp(rs.getDate("product_exp"));
			item.setIdcategories(rs.getInt("idcategories"));
			return item;
		}
	}

	/***
	 * 
	 * @return select table category
	 */
	public List<Product> findAll() {
		try {
			return db.query("select * from products", new ProductRowMapper());
		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
	

	public Product findById(int id) {
		return db.queryForObject("select * from products where idproduct=?", new ProductRowMapper(),
				new Object[] { id });
	}

	public int deleteById(int id) {
		return db.update("delete from products where idproduct=?", new Object[] { id });
	}

	

	
}
