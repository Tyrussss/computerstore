package com.cs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.model.Brand;
import com.cs.model.Category;
import com.cs.model.Product;
import com.cs.model.ProductDTO;
import com.cs.repository.ProductRepository.ProductDTORowMapper;
@Repository
public class ProductRepository {
	@Autowired
	JdbcTemplate db;
	
	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product item = new Product();
			item.setProductID (rs.getInt("ProductID"));
			item.setProductName (rs.getString("ProductName"));
			item.setProductDetails(rs.getString("ProductDetails"));
			item.setStock(rs.getInt("Stock"));
			item.setWarranty(rs.getInt("Warranty"));
			item.setPrice(rs.getInt("Price"));
			item.setBrandID(rs.getInt("BrandID"));
			item.setCategoryID(rs.getInt("CategoryID"));
			
			
			return item;
		}
	}
		/***
		 * 
		 * @return select table product
		 */
		public List<Product> findAll(){
			try {
				return db.query("select * from product", new ProductRowMapper());
				
			}catch (Exception ec) {
				ec.printStackTrace();
				throw new RuntimeException("Error!!");
				
			}
			
		}

		/*
		 * public List<Product> findTop4(){ try { return
		 * db.query("select top 4 from product", new ProductRowMapper());
		 * 
		 * }catch (Exception ec) { ec.printStackTrace(); throw new
		 * RuntimeException("Error!!");
		 * 
		 * }
		 * 
		 * }
		 */
		
		 

		
		/***
		 * 
		 * @return select 1 
		 */
		
		
		  public Product findByID(int id) { return
		  db.queryForObject("select * from Product where ProductID=?", new
		  ProductRowMapper(), new Object[] { id }); }
		 
		
		public int insert (Product Product) {
			return db.update("insert into Product ( ProductID,ProductName,ProductDetails,Stock,Warranty,Price,BrandID,CategoryID)" + "values( ?,?,?,?,?,?,?,?)",
					new Object[] {Product.getProductID(),Product.getProductName(), Product.getProductDetails(),Product.getStock(), Product.getWarranty(), Product.getPrice(), Product.getBrandID(), Product.getCategoryID()  });
		}
		
		public int update (Product Product) {
			return db.update("update Product "+"set ProductName = ?, ProductDetails = ?, Stock = ?, Warranty = ?, Price = ? " + "where ProductID = ?",
					new Object[] {Product.getProductName(), Product.getProductDetails(),Product.getStock(), Product.getWarranty(), Product.getPrice(), Product.getProductID() });
		}
		
		public int deleteById(int id) {
		return db.update("delete from Product where ProductID = ?", new Object[] { id });
		}
		public class ProductDTORowMapper implements RowMapper<ProductDTO> {
			 @Override
			 public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				 ProductDTO productDTO = new ProductDTO();
			     productDTO.setProductID(rs.getInt("ProductID"));
			     productDTO.setProductName(rs.getString("ProductName"));
			     productDTO.setProductDetails(rs.getString("ProductDetails"));
			     productDTO.setStock(rs.getInt("Stock"));
			     productDTO.setWarranty(rs.getInt("Warranty"));
			     productDTO.setPrice(rs.getInt("Price"));
			     productDTO.setBrandName(rs.getString("BrandName"));
			     productDTO.setCategoryName(rs.getString("CategoryName"));
			     return productDTO;
			 }
			}

		public List<ProductDTO> findTop5Products() {
		    String sql = "SELECT p.*, b.BrandName, c.CategoryName " +
		                 "FROM product p " +
		                 "INNER JOIN brand b ON p.BrandID = b.BrandID " +
		                 "INNER JOIN category c ON p.CategoryID = c.CategoryID " +
		                 "ORDER BY p.ProductID DESC " +
		                 "LIMIT 5";
		    return db.query(sql, new ProductDTORowMapper());
		}

		public List<ProductDTO> search(String searchText) {
			String sql = "SELECT p.*, b.BrandName, c.CategoryName " +
	                 "FROM product p " +
	                 "INNER JOIN brand b ON p.BrandID = b.BrandID " +
	                 "INNER JOIN category c ON p.CategoryID = c.CategoryID " +	                 
	                 "where b.BrandName LIKE ? or p.ProductName LIKE ? or p.ProductDetails LIKE ? ";
	    return db.query(sql,new Object[] {"%" + searchText + "%", "%" + searchText + "%", "%" + searchText + "%"}, new ProductDTORowMapper());
		}

		
}
