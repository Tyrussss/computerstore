package com.cs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ReviewRepository;
import com.cs.repository.UserRepository;

@Controller

public class testController {
	@Autowired
	UserRepository a;
	@RequestMapping("/test1")
	public String index(Model model) {
		//a = new CategoryRepository();
		model.addAttribute("test ", a.findAll() );
		return "test";
	}
	
}

