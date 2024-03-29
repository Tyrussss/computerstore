package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Discount;

@Repository
public class DiscountRepository {
	@Autowired
	JdbcTemplate db;
	class DiscountRowMapper implements RowMapper <Discount> {
		@Override
		public Discount mapRow (ResultSet rs, int rowNum) throws SQLException {
			Discount code = new Discount();
			code.setDiscountID(rs.getInt("DiscountID"));
			code.setCode (rs.getString("Code"));
			code.setType (rs.getString("Type"));
			code.setStartDate (rs.getString("StartDate"));
			code.setEndDate(rs.getString("EndDate"));
			code.setPercent(rs.getInt("Percent"));
			return code;
		}
	}
	/***
	 * 
	 * @return select table brand
	 */
		public List<Discount> findAll(){
			try {
				return db.query("select * from Discount", new DiscountRowMapper());
			}catch (Exception ec) {
				ec.printStackTrace();
				throw new RuntimeException("Error!!");
			}
		}
		/***
		 * 
		 * @return select 1 
		 */
		public Discount findByID (int id) {
			return db.queryForObject("select * from Discount where DiscountID = ?", new DiscountRowMapper(),
					new Object[] {id});
			
		}
		
		public int insert (Discount Discount) {
			return db.update("insert into Discount(DiscountID, Code, Type, StartDate, EndDate, Percent)" + "values(?,?,?,?,?,?",
					new Object[] {Discount.getDiscountID(),1 });
		}
		
		public int update (Discount Discount) {
			return db.update("update Discount " + "set Code = ?, Type = ?, StartDate = ?, EndDate = ?,Percent = ?" + "where DiscountID = ?",
					new Object[] {Discount.getDiscountID(),1,Discount.getDiscountID()});
		}
		
		public int deleteById (Discount Discount, int id) {
			return db.update("update Discount" + "set Code = ?, Type = ?, StartDate = ?, EndDate = ?, Percent = ?" + "where DiscountID = ?",
					new Object[] {Discount.getDiscountID(),0, id});
		}
		
	
}
