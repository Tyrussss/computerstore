package com.cs.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.ProductImage;

@Repository
public class ProductImageRepository {
	@Autowired
	JdbcTemplate db;
	
	class ProductImageRowMapper implements RowMapper <ProductImage> {
		@Override
		public ProductImage mapRow (ResultSet rs, int rowNum) throws SQLException {
			ProductImage img = new ProductImage();
			img.setProductImageID(rs.getInt("ProductImageID"));
			img.setImage (rs.getString("Image"));
			img.setMain (rs.getBoolean("Main"));
			img.setProductID(rs.getInt("ProductID"));
			return img;
		}
	}
	/***
	 * 
	 * @return select table brand
	 */
	public List<ProductImage> findAll(){
		try {
			return db.query("select * from ProductImage", new ProductImageRowMapper());
		}catch (Exception ec) {
			ec.printStackTrace();
			throw new RuntimeException("Error!!");
		}
	}
	
	/***
	 * 
	 * @return select 1 
	 */
	public ProductImage findByID (int id) {
		return db.queryForObject("select * from ProductImage where ProductImageID", new ProductImageRowMapper(),
				new Object[] {id});
	}
	public int insert (ProductImage ProductImage) {
		return db.update("insert into ProductImage (Image, Main,ProductID)"+ "values(?,?,?)",
				new Object[] {ProductImage.getImage(),ProductImage.getMain(),ProductImage.getProductID() });
	}
	public int update (ProductImage ProductImage) {
		return db.update("update ProductImage " + " set Image = ?, Main = ?, ProductID = ?" + "where ProductImageID = ? ",
				new Object[] {ProductImage.getImage(),ProductImage.getMain(), ProductImage.getProductID()});
		
	}
	public int deleteById (ProductImage ProductImage, int id) {
		return db.update("update ProductImage" + "set Image = ?, Main = ?, ProductID = ? ",
				new Object[] {ProductImage.getImage(), ProductImage.getMain(), ProductImage.getProductID()});
	}
}

