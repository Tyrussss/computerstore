package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cs.model.User;
import com.cs.repository.UserRepository.UserRowMapper;

public class UserRepository {
	@Autowired
	JdbcTemplate db;

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User item = new User();
			item.setUserID(rs.getInt("userID"));
			item.setUsername(rs.getString("username"));
			
			return item;
		}
	}
	
	/***
	 * 
	 * @return select table brand
	 */
	public List<User> findAll() {
		try {

			return db.query("select * from User", new UserRowMapper());

		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
}
