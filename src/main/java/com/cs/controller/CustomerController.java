package com.cs.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.model.ProductDTO;
import com.cs.model.User;
import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;
import com.cs.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class CustomerController {
	@Autowired
    private UserRepository userRepository;
	
	@RequestMapping("")
	public String index(HttpSession session, Model model, User user) {
		System.out.println(session.getAttribute("userID"));
		List<ProductDTO> top5Products = rep.findTop5Products();
        model.addAttribute("top5Products", top5Products);
		return "client/indexclient";
	}
	
	@GetMapping("/quickview")
	public String quickview() {
		
		return "client/productdetail";
	}	
	
	@PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam("regAvatar") MultipartFile avatarFile,
                               RedirectAttributes redirectAttributes) {

        try {
            String avatar = userRepository.saveAvatar(avatarFile);
            user.setAvatar(avatar);

            userRepository.registerUser(user);

            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to register user: " + e.getMessage());
        }

        return "redirect:/register";
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
    @Autowired
	ProductRepository rep;
	@Autowired
	CategoryRepository carep;
	@Autowired
	BrandRepository brcep;
	@GetMapping("/top")
	
		public String home(Model model) {
	        
	        return "client/indexclient";
	    }
		
}

