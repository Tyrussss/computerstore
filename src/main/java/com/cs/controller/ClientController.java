package com.cs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;

@Controller
@RequestMapping("")
public class ClientController {

    
    private ProductRepository productRepository;
    
    private CategoryRepository cate;
   

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", cate.findAll());

        return "client/view-products";
    }

    @GetMapping("/products/find")
    public String findProducts(@RequestParam("categoryId") int ProductID, Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", cate.findAll());

        return "client/view-products";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("product", productRepository.findAll());

        return "client/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("categories", cate.findAll());

        return "client/checkout";
    }
}
