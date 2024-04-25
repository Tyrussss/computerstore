package com.cs.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cs.model.User;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate db;

	private static final String SELECT_ALL_USERS = "SELECT * FROM User";
	private static final String INSERT_USER = "INSERT INTO User (Username, Password, Email, FullName, Phone, Address, Role, Newsletter, Avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE User SET Username = ?, Password = ?, Email = ?, FullName = ?, Phone = ?, Address = ?, Role = ?, Newsletter = ?, Avatar = ? WHERE UserID = ?";
	private static final String DELETE_USER_BY_ID = "DELETE FROM User WHERE UserID = ?";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE UserID = ?";
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM User WHERE Username = ? AND Password = ?";
	private static final String COUNT_USER_BY_EMAIL = "SELECT COUNT(*) FROM User WHERE Email = ?";
	private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM User WHERE Email = ?";
	private static final String AUTHENTICATE_USER = "SELECT * FROM User WHERE username = ? AND password = ?";

	public List<User> findAll() {
		return db.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	public int insert(User user) {
	    return db.update("INSERT INTO User (Username, Password, Email, FullName, Phone, Address, Role, Newsletter, Avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
	            user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), user.getPhone(), user.getAddress(), user.getRole(), user.getNewsletter(), user.getAvatar());
	}

	public int update(User user) {
		return db.update(UPDATE_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(),
				user.getPhone(), user.getAddress(), user.getRole(), user.getNewsletter(), user.getAvatar(),
				user.getUserID());
	}

	public int deleteById(int id) {
		return db.update(DELETE_USER_BY_ID, id);
	}

	public User findByID(int id) {
		return db.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
	}

	public User findByUserPw(String username, String password) {
		return db.queryForObject(SELECT_USER_BY_USERNAME_AND_PASSWORD, new UserRowMapper(), username, password);
	}

	public int findEmail(String email) {
		return db.queryForObject(COUNT_USER_BY_EMAIL, Integer.class, email);
	}

	public User findByEmail(String email) {
		return db.queryForObject(SELECT_USER_BY_EMAIL, new UserRowMapper(), email);
	}
	
	public int subscribe (User User) { return
			 db.update("insert into User ( Email, Role, Newsletter)" + " values (?,?,?)",
			 new Object[] {User.getEmail(), 2, true }); 
	} 

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserID(rs.getInt("UserID"));
			user.setUsername(rs.getString("Username"));
			user.setPassword(rs.getString("Password"));
			user.setEmail(rs.getString("Email"));
			user.setFullName(rs.getString("FullName"));
			user.setPhone(rs.getString("Phone"));
			user.setAddress(rs.getString("Address"));
			user.setRole(rs.getInt("Role"));
			user.setNewsletter(rs.getBoolean("Newsletter"));
			user.setAvatar(rs.getString("Avatar"));
			return user;
		}
	}
	
	public String saveAvatar(MultipartFile file) throws IOException {
        String sql = "INSERT INTO User (Avatar) VALUES (?) ";
        String Avatar = Objects.requireNonNull(file.getOriginalFilename());
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        db.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Avatar);
           
            return ps;
        });

        return Avatar; // Return file name or any other identifier
    }

	public void registerUser(User user) {        
        db.update(INSERT_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(),
                user.getPhone(), user.getAddress(), 0, user.getNewsletter(), user.getAvatar());
    }

    public void updateUser(User user) {        
        db.update(UPDATE_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(),
                user.getPhone(), user.getAddress(), 0, user.getNewsletter(), user.getAvatar(),
                user.getUserID());
    }
    
    public User authenticateUser(String username, String password) {        
        return db.queryForObject(AUTHENTICATE_USER, new Object[]{username, password}, new UserRowMapper());
    }

	/***
	 * 
	 * 
	 * @return select table User
	 */
	/*
	 * public List<User> findAll() { try {
	 * 
	 * return db.query("select * from User", new UserRowMapper());
	 * 
	 * }catch(Exception ec){ ec.printStackTrace(); throw new
	 * RuntimeException("Error!!"); } }
	 *//***
		 * @return select 1
		 *//*
			 * 
			 * public int insert (User User) { return db.
			 * update("insert into User ( Username, Password, Email, FullName, Phone, Address, Role, Newsletter, Avatar)"
			 * + " values (?,?,?,?,?,?,?,?,?)", new Object[]
			 * {User.getUsername(),User.getPassword(),User.getEmail(),User.getFullName(),
			 * User.getPhone(),User.getAddress(),User.getRole(),User.getNewsletter(),User.
			 * getAvatar() }); } public int update (User User) { return
			 * db.update("update User " +
			 * " set Username = ?, Password = ?, Email = ?, FullName = ?, Phone = ?, Address = ?, Role = ?, Newsletter = ?, Avatar = ?"
			 * + " where UserID = ?", new Object[] {
			 * User.getUsername(),User.getPassword(),User.getEmail(),User.getFullName(),User
			 * .getPhone(),User.getAddress(),User.getRole(),User.getNewsletter(),User.
			 * getAvatar() , User.getUserID() });
			 * 
			 * }
			 * 
			 * public int deleteById(User User, int id) { return
			 * db.update("delete from User where UserID = ?", new Object[] {id}); }
			 * 
			 * public int subscribe (User User) { return
			 * db.update("insert into User ( Email, Role, Newsletter)" + " values (?,?,?)",
			 * new Object[] {User.getEmail(), 2, true }); } public User findByID(int id) {
			 * try {
			 * 
			 * return db.queryForObject("select * from User where UserID = ?", new
			 * UserRowMapper(), new Object[] { id });
			 * 
			 * }catch(Exception ec){ ec.printStackTrace(); throw new
			 * RuntimeException("Error!!"); } }
			 * 
			 * public User findByUserPw(String username, String password) { try {
			 * 
			 * return
			 * db.queryForObject("select * from User where Username = ? and Password = ?",
			 * new UserRowMapper(), new Object[] { username, password });
			 * 
			 * }catch(Exception ec){ ec.printStackTrace(); throw new
			 * RuntimeException("Error!!"); } }
			 * 
			 * public int findEmail(String email) { try { String sql =
			 * "SELECT COUNT(*) FROM User WHERE Email = ?"; int count =
			 * db.queryForObject(sql, new Object[]{email}, Integer.class);
			 * 
			 * return count; }catch(Exception ec){ ec.printStackTrace(); throw new
			 * RuntimeException("Error!!"); } } public User findIDByEmail(String email) {
			 * try {
			 * 
			 * return db.queryForObject("select * from User where Email = ?", new
			 * UserRowMapper(), new Object[] { email });
			 * 
			 * }catch(Exception ec){ ec.printStackTrace(); throw new
			 * RuntimeException("Error!!"); } }
			 */

}
