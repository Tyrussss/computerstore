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
	private UserRepository userRep;

    @GetMapping("/login")
    public String showLogin() {
        return "client/login"; 
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "login") User user, Model model, HttpSession session, HttpServletRequest request) {
        
        String username = user.getUsername();
        String password = user.getPassword();
        
        User u = userRep.findByUserPw(username, password);
        model.addAttribute("loggeduser", u);        
        String role = checkUserRole(username, password);
        if (role != null) {
            model.addAttribute("username", u.getUsername());
            model.addAttribute("FullName", u.getFullName());
            session.setAttribute("UserID", u.getUserID());
            session.setAttribute("Username", u.getUsername());
            session.setAttribute("name", u.getFullName());
            session.setAttribute("role", u.getRole());
			/*
			 * if(role.equals("1")) { return "/admin/ad_index"; } else { return
			 * "/client/indexclient"; }
			 */
           return (role.equals("1")) ? "redirect:/admin" : "redirect:/logged";
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
        return "/login";
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
