package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Product;
@Repository
public class ProductRepository {
	@Autowired
	JdbcTemplate db;
	
	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product item = new Product();
			item.setProductID (rs.getInt("ProductID"));
			item.setProductName (rs.getString("ProductName"));
			item.setProductDetails(rs.getString("ProductDetails"));
			item.setStock(rs.getInt("Stock"));
			item.setWarranty(rs.getInt("Warranty"));
			item.setPrice(rs.getInt("Price"));
			item.setBrandID(rs.getInt("BrandID"));
			item.setCategoryID(rs.getInt("CategoryID"));
			return item;
		}
	}
		/***
		 * 
		 * @return select table product
		 */
		public List<Product> findAll(){
			try {
				return db.query("select * from product", new ProductRowMapper());
				
			}catch (Exception ec) {
				ec.printStackTrace();
				throw new RuntimeException("Error!!");
				
			}
			
		}
		/***
		 * 
		 * @return select 1 
		 */
		
		public Product findByID(int id) {
			return db.queryForObject("select * from Product where ProductID=?", new ProductRowMapper(),
					new Object[] { id });
		}
		
		public int insert (Product Product) {
			return db.update("insert into Product ( ProductID,ProductName,ProductDetails,Stock,Warranty,Price,BrandID,CategoryID)" + "values( ?,?,?,?,?,?,?,?)",
					new Object[] {Product.getProductID(),Product.getProductName(), Product.getProductDetails(),Product.getStock(), Product.getWarranty(), Product.getPrice(), Product.getBrandID(), Product.getCategoryID()  });
		}
		
		public int update (Product Product) {
			return db.update("update Product "+"set ProductName = ?, ProductDetails = ?, Stock = ?, Warranty = ?, Price = ?" + "where ProductID = ?",
					new Object[] {Product.getProductName(), Product.getProductDetails(),Product.getStock(), Product.getWarranty(), Product.getPrice(), Product.getProductID() });
		}
		
		public int deleteById (Product Product, int id) {
			return db.update("delete from Product where Product ID=?", new Object[] {id});
		}
	
}
