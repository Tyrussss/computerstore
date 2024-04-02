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
import com.cs.repository.UserRepository;

@Controller
@RequestMapping("/admin")

public class AdminController {
	@Autowired
	UserRepository userRep;
	
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		
		model.addAttribute("user", userRep.findAll());
		model.addAttribute("userReg", new UserReg());
		
		List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/account";
	}
	@GetMapping("/register")
    public String showForm(Model model) {
        
        model.addAttribute("user", new User());
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/account";
    }
	
	@PostMapping("/register/save")
    public String saveForm(Model model, User user) {
        
        model.addAttribute("user", userRep.insert(user));
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/register";
    }
	
	
}
