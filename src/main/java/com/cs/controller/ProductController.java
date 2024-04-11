package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.model.Brand;
import com.cs.model.Category;
import com.cs.model.Product;
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
		return "/admin/product";
	}
	@GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("user", new Product());
       
        return "/admin/newproduct";
    }
	
	

    @PostMapping("/product/save")
    public String createProduct(@ModelAttribute Product product) {
        rep.insert(product);
        return "redirect:/admin/product";
    }
    
    @GetMapping("/brand/new")
    	public String newBrand (Model model) {
    		model.addAttribute ("brand", new Brand());
    		return "/admin/newbrand";
    	}
    @PostMapping ("/brand/save")
    public String createBrand (@ModelAttribute Brand brand) {
    	brcep.insert(brand);
    	return "redirect:/admin/product";
    }
    
	
	  @GetMapping("/category/new") public String newCategory (Model model) {
	  model.addAttribute ("category", new Category()); return "/admin/newcate"; }
	 
	@PostMapping ("/category/save")
	public String createCategory (@ModelAttribute Category category) {
		carep.insert(category);
		return "redirect:/admin/product";
	}
  
}
