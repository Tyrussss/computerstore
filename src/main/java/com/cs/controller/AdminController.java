package com.cs.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.model.User;
import com.cs.model.UserReg;
import com.cs.repository.UserRegRepository;
import com.cs.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")

public class AdminController {
	@Autowired
	UserRepository userRep;
	UserRegRepository userRegRep;
	
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		
		model.addAttribute("user", userRep.findAll());
				
		List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/account";
	}
	@GetMapping("/account/register")
    public String showForm(Model model) {
        
        model.addAttribute("userReg", new UserReg());
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/register";
    }
	
	/* <--- user register---> */
	@PostMapping("/account/save")
    public String saveForm(Model model, User user, HttpServletRequest request) {
        
        userRep.insert(user);        
        
		return "admin/account";
    }
	
	/* <--- admin create---> */
	@PostMapping("/account/create")
    public String create(Model model, UserReg userReg, HttpServletRequest request) {
		/*
		 * userReg = new UserReg(); String avatar = "avatar.png";
		 * userReg.setUsername(request.getParameter("username"));
		 * userReg.setEmail(request.getParameter("email"));
		 * userReg.setFullName(request.getParameter("name"));
		 * userReg.setAvatar(request.getParameter("avatar"));
		 */
        userRegRep.insert(userReg);
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/account";
    }
	
	/* <--- user subscribe---> */
	@PostMapping("/account/subscriber")
    public String subscribe(Model model, UserReg userReg, HttpServletRequest request) {
        
        userRegRep.insert(userReg);
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subscriber");
        model.addAttribute("listRole", listRole);
		return "admin/register";
    }
}
