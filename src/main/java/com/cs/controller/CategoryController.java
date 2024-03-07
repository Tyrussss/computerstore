package com.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.model.Category;
import com.cs.repository.CategoryRepository;


@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	CategoryRepository rep;

	@RequestMapping(value="/product",method = RequestMethod.GET)
	public String showCateList(Model model) {
	    model.addAttribute("categories", rep.findAll());
	    return "admin/products";
	}
	
	/*
	 * @RequestMapping(value="/categories",method = RequestMethod.GET)
	 * List<Category> all() { return rep.findAll(); }
	 */
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	Category getCategory(@PathVariable("id") int id) {
		return rep.findById(id);
	}
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public int saveCategory(@Validated @RequestBody Category item) {
		return rep.insert(item);
	}
	@RequestMapping(value = "/categories",method = RequestMethod.PUT)
	public int updateCategory(@Validated @RequestBody Category item) {
		return rep.update(item);
	}
@RequestMapping(value = "/categories/{id}",method = RequestMethod.DELETE)
	public int deleteCategory(@PathVariable("id")int id) {
		return rep.deleteById(id);
	}
}
