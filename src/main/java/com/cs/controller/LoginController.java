package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLogin() {
        return "client/login"; // Trả về trang đăng nhập
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm") User user, Model model, HttpSession session, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getFullName();

        String role = checkUserRole(username, password);
        if (role != null) {
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            session.setAttribute("role", role);
            return (role.equals("1")) ? "/admin/ad_index" : "/client/indexclient";
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
