package com.cs.controller;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.model.UserReg;
import com.cs.repository.CategoryRepository;
import com.cs.repository.ProductRepository;
import com.cs.repository.UserRegRepository;

@Controller
@RequestMapping("")
public class ClientController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository cate;
    @Autowired
    private UserRegRepository userRegRep;
   

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", cate.findAll());

        return "client/view-products";
    }

    @GetMapping("/products/find")
    public String findProducts(@RequestParam("categoryId") int ProductID, Model model) {
        model.addAttribute("products", productRepository.findByID(ProductID));
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
    
    @GetMapping("/register")
    public String clientReg(Model model, UserReg userReg) {
        model.addAttribute("userReg", userRegRep.findAll());

        
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "client/register";
    }
}
