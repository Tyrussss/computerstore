package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.repository.CategoryRepository;
import com.cs.repository.UserRepository;

@Controller
@RequestMapping("/admin")
@Repository("UserRepository")

public class AdminController {
		
	UserRepository userRep;
	@Autowired
	CategoryRepository cateRep;
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@GetMapping("/account")
	public String account(Model model) {
		//userRep = new UserRepository();
		model.addAttribute("user", cateRep.findAll());
		return "admin/account";
	}
	
	
}
