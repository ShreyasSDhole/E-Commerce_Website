package com.ecom.demo.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.dto.CategoryDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.services.admin.category.CategoryService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
public class AdminCategoryController 
{
	public CategoryService categoryService;

	public AdminCategoryController(CategoryService categoryService) 
	{
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto)
	{
		Category category = categoryService.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	public ResponseEntity<List<Category>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
}
