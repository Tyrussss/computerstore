package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.model.Product;
import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;


@Controller
@RequestMapping("")
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
	@GetMapping("/product/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new Product());
        return "newProduct";
    }

    @PostMapping("/product")
    public String createUser(@ModelAttribute Product product) {
        rep.insert(product);
        return "redirect:/";
    }
}
