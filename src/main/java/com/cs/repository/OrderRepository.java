package com.cs.repository;

import com.cs.model.Cart;
import com.cs.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderRepository {
	@Autowired
	JdbcTemplate db;
	
	class OrderRowMapper implements RowMapper<Order> {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order item = new Order();
			item.setOrderID (rs.getInt("OrderID"));
			item.setUserID (rs.getInt("UserID"));
			item.setPaymentStatus (rs.getBoolean("PaymentStatus"));
			item.setCreated_Date (rs.getDate("Created_Date"));
			return item;
		}
	}

	public int insert(Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		db.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO `Orders` (UserID, PaymentStatus, Created_Date) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, order.getUserID());
			ps.setObject(2, order.getPaymentStatus());
			ps.setObject(3, order.getCreated_Date());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

}
