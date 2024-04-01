package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.ProductInfo;

@Repository
public class ProductInfoRepository {
	@Autowired
	JdbcTemplate db;
	
	class ProductInfoRowMapper implements RowMapper<ProductInfo>{
		@Override
		public ProductInfo mapRow (ResultSet rs, int rowNum) throws SQLException {
			ProductInfo info = new ProductInfo();
			info.setProductInfoID(rs.getInt("ProductInfoID"));
			info.setName(rs.getString("Name"));
			info.setContent(rs.getString("Content"));
			info.setProductID (rs.getInt("ProductID"));
			return info;
		}
	}
	/***
	 * 
	 * @return select table brand
	 */
	public List<ProductInfo> findAll(){
		try {
			return db.query("select * from ProductInfo", new ProductInfoRowMapper ());
			
		}catch (Exception ec) {
			ec.printStackTrace();
			throw new RuntimeException("Error!!");
		}
	}
	public ProductInfo findByID (int id) {
		return db.queryForObject("select * from ProductInfo where ProductInfoID = ?", new ProductInfoRowMapper(), 
				new Object[] {id});
	}
	
	public int insert (ProductInfo ProductInfo) {
		return db.update("insert into ProductInfo (Name, Content, ProductID" + "values(?,?,?)",
				new Object[] {ProductInfo.getName(), ProductInfo.getContent(), ProductInfo.getProductID()});
	}
	
	public int update (ProductInfo ProductInfo) {
		return db.update("update ProductID " + "set Name = ?, Content = ?, ProductID = ?" + " where ProductInfoID = ?",
				new Object[] {ProductInfo.getName(),ProductInfo.getContent(), ProductInfo.getProductID()});
		
	} 
	
	public int deleteById (ProductInfo ProductInfo, int id) {
		return db.update("update ProductInfo " + "set Name = ?, Content = ?, ProductID = ?",
				new Object[] {ProductInfo.getName(), ProductInfo.getContent(),ProductInfo.getProductID()});
	}
}
