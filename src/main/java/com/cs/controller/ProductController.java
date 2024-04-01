package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	ProductRepository rep;
	@Autowired
	CategoryRepository carep;
	@Autowired
	BrandRepository brcep;
	@GetMapping("/product")
	public String index (Model model ) {
		model.addAttribute("product", rep.findAll());
		model.addAttribute("category", carep.findAll());
		model.addAttribute("brand", brcep.findAll());
		return "admin/product";
	}
}
