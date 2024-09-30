package com.ecom.demo.services.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.demo.dto.CategoryDto;
import com.ecom.demo.entity.Category;
import com.ecom.demo.repository.CategoryRepository;

// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) 
	{
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public Category createCategory(CategoryDto categoryDto)
	{
		Category category = new Category(); 
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories()
	{
		return categoryRepository.findAll();
	}
	
}
