package com.cs.repository;

import com.cs.model.Cart;
import com.cs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {
	@Autowired
	JdbcTemplate db;
	
	class CartRowMapper implements RowMapper<Cart> {
		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart item = new Cart();
			item.setCartID (rs.getInt("CartID"));
			item.setProductID (rs.getInt("ProductID"));
			item.setUserID (rs.getInt("UserID"));
			item.setQuantity (rs.getInt("Quantity"));
			item.setPrice (rs.getFloat("Price"));
			item.setTotalPrice (rs.getFloat("TotalPrice"));
			item.setProductName (rs.getString("ProductName"));
			return item;
		}
	}
		/***
		 * 
		 * @return select table cart
		 */
		public List<Cart> findAll(){
			try {
				return db.query("select * from cart", new CartRowMapper());
				
			}catch (Exception ec) {
				ec.printStackTrace();
				throw new RuntimeException("Error!!");
				
			}
			
		}
		/***
		 * 
		 * @return select 1 
		 */
		
		public Cart findByID(int id) {
			return db.queryForObject("select * from Cart where CartID=?", new CartRowMapper(),
					new Object[] { id });
		}

		public List<Cart> findByUserID(int userId) {
			return db.query("SELECT c.CartID as CartID, c.ProductID  as ProductID, c.UserID as UserID, " +
					"c.Quantity as Quantity, c.Price as Price, c.TotalPrice as TotalPrice, p.ProductName as ProductName FROM Cart c INNER JOIN Product p ON c.ProductID = p.ProductID WHERE UserID=?", new CartRowMapper(), userId);
		}


	public int insert (Cart Cart) {
			return db.update("insert into Cart ( ProductID,UserID,Quantity,Price,TotalPrice)" + "values( ?,?,?,?,?)",
					new Object[] {Cart.getProductID(),Cart.getUserID(), Cart.getQuantity(),Cart.getPrice(), Cart.getPrice() * Cart.getQuantity()});
		}
		
		public int update (Cart Cart) {
			return db.update("update Cart "+"set ProductID = ?, UserID = ?, Quantity = ?, Price = ?, TotalPrice = ?" + "where CartID = ?",
					new Object[] {Cart.getProductID(), Cart.getUserID(),Cart.getQuantity(), Cart.getPrice(), Cart.getQuantity() * Cart.getPrice(), Cart.getCartID() });
		}
		
		public int deleteById (int id) {
			return db.update("delete from Cart where CartID=?", new Object[] {id});
		}
		public double getTotal(int accountId) {
	        String query = "SELECT SUM(totalPrice) AS total " +
	                       "FROM cart " +
	                       "WHERE UserId = ?";
	        return db.queryForObject(query, Double.class, accountId);
	    }
		

		
}
