package com.springboot.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.CategoryDto;

@Service
public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(int id , CategoryDto categoryDto);

	public ResponseEntity<?>  deleteCategory(int id);
	
	public List<CategoryDto> getAllCategory();
}
