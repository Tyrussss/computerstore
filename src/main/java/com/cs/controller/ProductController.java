package com.cs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.model.Brand;
import com.cs.model.Category;
import com.cs.model.Product;
import com.cs.model.User;
import com.cs.repository.BrandRepository;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;




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
		model.addAttribute("products", rep.findAll());
		model.addAttribute("category", carep.findAll());
		model.addAttribute("brand", brcep.findAll());
		return "/admin/product";
	}
	@GetMapping("/product/new")
    public String newProduct(Model model) {
		
        model.addAttribute("products", rep.findAll());
		model.addAttribute("categories", carep.findAll());
		model.addAttribute("brands", brcep.findAll());
        return "/admin/newproduct";
    }
	
	
    @PostMapping("/product/save")
    public String createProduct(Model model, Product product, HttpServletRequest request) {
        rep.insert(product);
        return "redirect:/admin/product";
    }
    
	
    @GetMapping("product/edit/{id}")
	
	  public String showUpdateForm(@PathVariable("id") int id, Model model) {
	  Product product = rep.findByID(id); 
	  model.addAttribute("products",rep.findAll()); 
	  model.addAttribute("categories", carep.findAll());
	  model.addAttribute("product", product);
	  return "admin/editproduct"; }
	 
	/*
	 * public String showUpdateForm(@PathVariable("id") int id, Model model) {
	 * Product product = rep.findById(id); List<Category> categories =
	 * carep.findAll(); List<Brand> brands = brcep.findAll();
	 * model.addAttribute("product", product); model.addAttribute("categories",
	 * categories); model.addAttribute("brands", brands); return
	 * "/admin/editproduct"; }
	 */

    @PostMapping ("/product/edit/save")
    public String updateProduct (@ModelAttribute Product product) {    	
        rep.update(product);  // Saves the updated user object
        return "redirect:/admin/product"; // Redirect to user list page)
    }
	
	  @GetMapping("/product/delete/{id}") 
	  public String deleteProduct(@PathVariable int id) { 
		  rep.deleteById( id); 
		  return "redirect:/admin/product"; }
	 
    
	
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
