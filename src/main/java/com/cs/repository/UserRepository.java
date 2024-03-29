package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.User;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate db;
	
	class UserRowMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserID(rs.getInt("UserID"));
			user.setUsername (rs.getString("Username"));
			user.setPassword(rs.getString("Password"));
			user.setEmail(rs.getString("Email"));
			user.setFullName(rs.getString("FullName"));
			user.setPhone(rs.getString("Phone"));
			user.setAddress(rs.getString("Address"));
			user.setRole (rs.getInt("Role"));
			user.setNewsletter(rs.getInt("Newsletter"));
			user.setAvatar(rs.getString("Avatar"));
			return user;
		}
	}
	
	
	/***
	 * 
	 	
	 * @return select table User
	 */
	public List<User> findAll() {
		try {

			return db.query("select * from User", new UserRowMapper());

		}catch(Exception ec){
			ec.printStackTrace();
			throw new RuntimeException("Error!!");	
		}
	}
	/***
	 * @return select 1 
	 */
	
	public int insert (User User) {
		return db.update("insert into User (Username, Password, Email, FullName, Phone, Address, Role, Newsletter, Avartar)" + " values (?,?,?,?,?,?,?,?,?)",
				new Object[] {User.getUserID(),1});
	}
	public int update (User User) {
		return db.update("update User " + " set Username = ?, Password = ?, Email = ?, FullName = ?, Phone = ?, Address = ?, Role = ?, Newsletter = ?, Avatar = ?" + " where UserID = ?", 
				new Object[] { User.getUsername(), 1, User.getUserID() });
		
	}
	
	public int deleteById(User User, int id) {
		return db.update("delete from User where UserID = ?",
				new Object[] {id});
	}
		
}
