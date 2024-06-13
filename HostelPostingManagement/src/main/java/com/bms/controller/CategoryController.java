package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bms.entity.Category;
import com.bms.services.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/list-category")
	public String listCategoryPage(Model model) {
		List<Category> listCategories = categoryService.findAll();
		model.addAttribute("listCategories", listCategories);
		return "listCategory";
	}
	
	@GetMapping("edit-category")
	public String editCategoryPost(Model model,@Param("categoryId") int categoryId) {
		Category category = categoryService.findById(categoryId);
		model.addAttribute("category", category);
		return "add-category";
	}
	
	@GetMapping("/add-category")
	public String addCategoryPageGet() {
		return "add-category";
	}
	
	@PostMapping("/add-category")
	public String addCategoryPagePost(Category category,Model model) {
		boolean isInsertCategory = categoryService.insertCategory(category);
		if(!isInsertCategory) {
			model.addAttribute("result", "Thêm/sửa danh mục thất bại.");
			return "add-category";
		}
		model.addAttribute("result", "Thêm/sửa danh mục thành công.");
		return "add-category";
	}
}
