package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cs.model.User;
import com.cs.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/login")
    public String showLogin(User user, Model model, HttpSession session, HttpServletRequest request) {
        return "client/login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute(name = "login") User user, Model model, HttpSession session, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();

        // Validate user credentials and fetch user details
        User authenticatedUser = userRepository.authenticateUser(username, password);

        if (authenticatedUser != null) {
        	
        	// Add authenticatedUser object to the session
            model.addAttribute("authenticatedUser", authenticatedUser);
            // Add user details to the session        	
            session.setAttribute("UserID", authenticatedUser.getUserID());
            session.setAttribute("username", authenticatedUser.getUsername());
            session.setAttribute("fullName", authenticatedUser.getFullName());
            session.setAttribute("avatar", authenticatedUser.getAvatar());
            
            int role = authenticatedUser.getRole();

            session.setAttribute("role", role);

            return (role == 1) ? "redirect:/admin" : "redirect:/";
        } else {
            model.addAttribute("error", "Incorrect Username & Password");
            return "/client/login";
        }
    }
    @PostMapping("/loginA")
    public String loginA(@ModelAttribute(name = "login") User user, Model model, HttpSession session, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getFullName();

        String role = checkUserRole(username, password);
        if (role != null) {
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            session.setAttribute("role", role);
			/*
			 * if(role.equals("1")) { return "/admin/ad_index"; } else { return
			 * "/client/indexclient"; }
			 */

           return (role.equals("1")) ? "redirect:/admin" : "redirect:/";

        } else {
            model.addAttribute("error", "Incorrect UserName & Password");
            return "/client/login";   
        }
        
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logoutPost(HttpSession session) {
        session.invalidate();
        return "/client/login";
    }

    // Phương thức kiểm tra thông tin đăng nhập
    private String checkUserRole(String username, String password) {
        String sql = "SELECT role FROM User WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, username, password);
        } catch (Exception e) {
            return null;
        }
    }
}
