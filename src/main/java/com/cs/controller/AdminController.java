package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.repository.CategoryRepository;
import com.cs.repository.UserRepository;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	UserRepository userRep;
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@GetMapping("/account")
	public String account(Model model) {
		model.addAttribute("user", userRep.findAll());
		return "admin/account";
	}
	
	
}
