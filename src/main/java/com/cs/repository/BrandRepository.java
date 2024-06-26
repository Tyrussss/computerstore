package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Brand;

@Repository
public class BrandRepository {
	@Autowired
	JdbcTemplate db;

	class BrandRowMapper implements RowMapper<Brand> {
		@Override
		public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
			Brand item = new Brand();
			item.setBrandID(rs.getInt("BrandID"));
			item.setBrandName(rs.getString("BrandName"));
			item.setDelStatus(rs.getBoolean("DelStatus"));
			return item;
		}
	}
	
	/***
	 * 
	 * @return select table brand
	 */
	public List<Brand> findAll() {
		try {

			return db.query("select * from Brand where DelStatus = 1", new BrandRowMapper());

		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
	
	public String findBrandNameByBrandID(int brandID) {
	    return db.queryForObject("SELECT BrandName FROM Brand WHERE BrandID = ?", String.class, brandID);
	}
		
	public Brand findByID(int id) {
		return db.queryForObject("select * from Brand where BrandID=?", new BrandRowMapper(),
				new Object[] { id });
	}
	
//	public int deleteById(int id) {
//		return db.update("delete from Brand where BrandID=?", new Object[] { id });
//	}

	public int insert(Brand Brand) {
		return db.update("insert into Brand ( BrandName, DelStatus) " + "values( ?, ?)",
				new Object[] { Brand.getBrandName(), 1 });
	}

	public int update(Brand Brand) {
		return db.update("update Brand " + " set BrandName = ?, DelStatus = ?" + " where BrandID = ?",
				new Object[] { Brand.getBrandName(), 1, Brand.getBrandID() });
	}
	public int deleteById(Brand Brand, int id) {
		return db.update("update Brand " + " set BrandName = ?, DelStatus = ?" + " where BrandID = ?",
				new Object[] { Brand.getBrandName(), 0, id });
	}
}
