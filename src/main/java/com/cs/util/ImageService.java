package com.cs.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cs.model.User;

@Service
public class ImageService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void saveImg(User user, MultipartFile imageFile) throws IOException {
    // Generate unique filename
    String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
    
    // Save image to designated directory (on server)
    String uploadPath = "/static/clic/img/avatar/"; // Replace with your upload directory path
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    File image = new File(uploadPath + fileName);
    imageFile.transferTo(image);
    
    // Update product with image path
    user.setAvatar(fileName);
    
    // Save product details to database using JdbcTemplate
    String sql = "INSERT INTO user (Avatar) VALUES (?)";
    jdbcTemplate.update(sql, user.getAvatar());
  }
  
  private String generateUniqueFileName(String fileName) {
	return fileName;
    // Implement logic to generate a unique filename (e.g., using timestamps)
  }
}