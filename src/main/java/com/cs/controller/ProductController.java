package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	CategoryRepository rep;
	ProductRepository product_rep;

	@RequestMapping(value="/products",method = RequestMethod.GET)
	public String showCateList(Model model) {
	    model.addAttribute("categories", rep.findAll());
	    model.addAttribute("products", product_rep.findAll());
	    return "admin/products";
	}
}
