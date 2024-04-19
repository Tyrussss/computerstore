package com.cs.repository;

import com.cs.model.Cart;
import com.cs.model.Order;
import com.cs.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailRepository {
	@Autowired
	JdbcTemplate db;

	class OrderDetailRowMapper implements RowMapper<OrderDetail> {
		@Override
		public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDetail item = new OrderDetail();
			item.setOrderID (rs.getInt("OrderID"));
			item.setProductID (rs.getInt("ProductID"));
			item.setQuantity (rs.getInt("Quantity"));
			item.setDiscountID (rs.getInt("DiscountID"));
			item.setSubTotal (rs.getFloat("SubTotal"));
			item.setTotal (rs.getFloat("Total"));
			return item;
		}
	}



	public int insert (OrderDetail orderDetail) {
		System.out.println(orderDetail);
		return db.update("insert into OrderDetail (OrderID, ProductID, Quantity, SubTotal, Total)" + "values(?,?,?,?,?)",
				new Object[] {orderDetail.getOrderID(),orderDetail.getProductID(), orderDetail.getQuantity(), orderDetail.getSubTotal(), orderDetail.getTotal()});
	}

}
