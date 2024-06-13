package com.bms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Category;
import com.bms.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public Category findById(int id) {
		return categoryRepository.findById(id).get();
	}

	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	public boolean insertCategory(Category category) {
		return categoryRepository.save(category) != null;
	}
}
