package com.cs.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.model.User;
import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;
import com.cs.repository.ReviewRepository;
import com.cs.repository.UserRepository;

@Controller

public class testController {
	@Autowired
	ProductRepository a;
	@Autowired
    private UserRepository userRepository;
	@RequestMapping("/comment")
	public String index(Model model) {
		//a = new CategoryRepository();
		model.addAttribute("test ", a.findAll() );
		return "/client/reviewproduct";
	}
	
	@PostMapping("/register1111")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam("regAvatar") MultipartFile avatarFile,
                               @RequestParam("email") String email,
                               RedirectAttributes redirectAttributes) {

        try {
            String avatar = userRepository.saveAvatar(avatarFile);
            user.setAvatar(avatar);
            
         // Check if the email exists in the database
            User existingUser = userRepository.findByEmail(email);

            if (existingUser != null) {
            	// Update the existing user entity with new details
                existingUser.setUserID(existingUser.getUserID());
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(email);
                existingUser.setFullName(user.getFullName());
                existingUser.setPhone(user.getPhone());
                existingUser.setNewsletter(user.getNewsletter());
                existingUser.setAddress(user.getAddress());
                existingUser.setAvatar(avatar);
            	userRepository.updateUser(existingUser);
            }
            else {
            	userRepository.registerUser(user);
            }

            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to register user: " + e.getMessage());
        }

        return "redirect:/login";
    }
	
	
}

