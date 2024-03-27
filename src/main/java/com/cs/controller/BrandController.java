package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.model.Brand;
import com.cs.repository.BrandRepository;

public class BrandController {
	@Autowired
	BrandRepository rep;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showBrandList(Model model) {
		model.addAttribute("brands", rep.findAll());
		return "admin/products";
	}

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public String getBrand(@PathVariable("id") int id, Model model) {
		model.addAttribute("brands", rep.findByID(id));
		return "admin/brand/id";
	}

	@RequestMapping(value = "/brand", method = RequestMethod.POST)
	public int saveBrand(@Validated @RequestBody Brand item) {
		return rep.insert(item);
	}

	@RequestMapping(value = "/brand", method = RequestMethod.PUT)
	public int updateBrand(@Validated @RequestBody Brand item) {
		return rep.update(item);
	}

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.PUT)
	public int deleteBrand(@Validated @RequestBody Brand item, @PathVariable("id") int id) {
		return rep.deleteById(item, id);
	}
}
