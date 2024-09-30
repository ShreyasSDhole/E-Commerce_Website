package com.ecom.demo.services.admin.category;

import java.util.List;

import com.ecom.demo.dto.CategoryDto;
import com.ecom.demo.entity.Category;

public interface CategoryService 
{
	Category createCategory(CategoryDto categoryDto);
	
	List<Category> getAllCategories();
}
