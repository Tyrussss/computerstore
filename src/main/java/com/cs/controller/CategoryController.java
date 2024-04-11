package com.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.model.Category;
import com.cs.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	CategoryRepository rep;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showCateList(Model model) {
		model.addAttribute("category", new Category());
		return "admin/category";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getCategory(@PathVariable("id") int id, Model model) {
		model.addAttribute("categories", rep.findByID(id));
		return "admin/categories/id";
	}
	
	/*
	 * @GetMapping("/new") public String showAddForm(Model model) {
	 * model.addAttribute("category", new Category()); return "admin/newcate"; }
	 */

	/*
	 * @PostMapping("/save") public String addCategory(@ModelAttribute("category")
	 * Category category) { rep.insert(category); return "redirect:/admin/category";
	 * }
	 */
	/*
	 * @RequestMapping(value = "/new", method = RequestMethod.GET) public String
	 * saveForm(Model model) { Category cate = new Category();
	 * model.addAttribute("cate", cate);
	 * 
	 * return "admin/newcate"; }
	 */
	/*
	 * @RequestMapping(value = "/save", method = RequestMethod.POST)
	 * 
	 * public String addCategory(@ModelAttribute("category") Category category) {
	 * rep.insert(category); return "redirect:/admin/category"; }
	 */

	@RequestMapping(value = "/categories", method = RequestMethod.PUT)
	public int updateCategory(@Validated @RequestBody Category item) {
		return rep.update(item);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
	public int deleteCategory(@Validated @RequestBody Category item, @PathVariable("id") int id) {
		return rep.deleteById(item, id);
	}
}