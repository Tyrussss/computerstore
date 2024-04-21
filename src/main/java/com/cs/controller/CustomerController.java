package com.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class CustomerController {
	@RequestMapping("")
	public String index() {
		
		return "client/indexclient";
	}
	
	@GetMapping("/logged")
	public String logged_index() {
		
		return "client/customer_index";
	}	
}

