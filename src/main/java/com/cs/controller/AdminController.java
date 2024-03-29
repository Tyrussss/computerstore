package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.repository.UserRepository;

@Controller
@RequestMapping("/admin")

public class AdminController {
		
	UserRepository userRep = new UserRepository();
	
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		
		model.addAttribute("user", userRep.findAll());
		return "admin/account";
	}
	
	
}
