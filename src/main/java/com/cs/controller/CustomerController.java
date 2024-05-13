package com.cs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.model.Product;
import com.cs.model.ProductDTO;
import com.cs.model.User;
import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;
import com.cs.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


import jakarta.servlet.http.HttpSession;

@Controller

public class CustomerController {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	ProductRepository rep;
	@Autowired
	CategoryRepository carep;
	@Autowired
	BrandRepository brcep;
	@Autowired
    private ResourceLoader resourceLoader;
	
	// Method to save the uploaded avatar file
    private String saveAvatar(MultipartFile avatarFile) throws IOException {
        // Define the directory where you want to save the avatar files
        String uploadDirectory = "/clic/img/";

     // Generate a unique file name for the avatar
        String fileName = System.currentTimeMillis() + "_" + avatarFile.getOriginalFilename();

        // Obtain a Resource representing the upload directory within the classpath
        Resource resource = resourceLoader.getResource("classpath:/" + uploadDirectory);

        // Get the file path within the upload directory
        String filePath = resource.getFile().getAbsolutePath() + File.separator + fileName;

        // Save the avatar file to the directory
        Files.write(Paths.get(filePath), avatarFile.getBytes());

        // Return the path or identifier of the saved avatar file
        return fileName;
    }
	
	@RequestMapping("")
	public String index(HttpSession session, Model model, User user) {
		List<ProductDTO> top5Products = rep.findTop5Products();
        model.addAttribute("top5Products", top5Products);
		return "client/indexclient";
	}
	

	
	@PostMapping("/search")
	public String searchResults(HttpSession session, Model model, User user,
            @RequestParam("searchText") String searchText) {		
		List<ProductDTO> search = rep.search(searchText);
        model.addAttribute("search", search);
		return "client/searchResults";
	}
	
	@GetMapping("/quickview/{id}")
	public String quickview(@PathVariable("id") int id, Model model) {	
		Product product = rep.findByID(id);
		/* Map<String, Object> product = rep.findByID(id); */
		List<Map<String, Object>> productImage = rep.findDetailProductImage(id);
		List<Map<String, Object>> productContent = rep.findDetailProductContent(id);
		
		model.addAttribute("product", product);
		model.addAttribute("productImage", productImage);
		model.addAttribute("productContent", productContent);
		return "client/productdetail";
	}	
	
	@GetMapping("/register")
	public String register() {		
		return "client/register";
	}	
	@GetMapping("/profile/{id}")
	public String editU(@PathVariable("id") int id, Model model) {	
		User user = userRepository.findByID(id);
		model.addAttribute("user",user);
		return "/client/edituser";
	}		
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user,
	                           @RequestParam("regAvatar") MultipartFile avatarFile,
	                           @RequestParam("email") String email,
	                           RedirectAttributes redirectAttributes) {

	    try {
	    	// Check if the avatar file is not empty
	        if (!avatarFile.isEmpty()) {
	            // Process the avatar file here
	            String avatar = saveAvatar(avatarFile); // You need to implement this method
	            user.setAvatar(avatar);
	        }

	        // Check if the email exists in the database
	        int isPresent = userRepository.findEmail(email);

	        if (isPresent != 0) {
	        	 User existingUser = userRepository.findByEmail(email);
	            // Update the existing user entity with new details
	            existingUser.setUsername(user.getUsername());
	            existingUser.setPassword(user.getPassword());
	            existingUser.setFullName(user.getFullName());
	            existingUser.setPhone(user.getPhone());
	            existingUser.setNewsletter(user.getNewsletter());
	            existingUser.setAddress(user.getAddress());
	            existingUser.setAvatar(saveAvatar(avatarFile));

	            userRepository.updateUser(existingUser); // Save the updated user
	        } else {
	            userRepository.registerUser(user); // Register a new user
	        }

	        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully!");
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to register user: " + e.getMessage());
	    }

	    return "redirect:/login";
	}

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user,
                           @RequestParam("editAvatar") MultipartFile avatarFile,
                           RedirectAttributes redirectAttributes) {

        try {
            if (!avatarFile.isEmpty()) {
                String avatar = userRepository.saveAvatar(avatarFile);
                user.setAvatar(avatar);
            }

            userRepository.updateUser(user);

            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user: " + e.getMessage());
        }

        return "redirect:/";
    }
    
	@GetMapping("/top")
	
		public String home(Model model) {
	        
	        return "client/indexclient";
	    }
	
	@PostMapping("/checkUsername")
    public Map<String, Boolean> checkUsernameAvailability(@RequestBody Map<String, String> requestData) {
        String username = requestData.get("username");
        boolean exists = userRepository.existsByUsername(username);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
		
}

