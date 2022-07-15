package com.springboot.app.serviceimlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Categories;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.CategoriesRepo;
import com.springboot.app.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoriesRepo categoriesRepo;

	@Autowired
	ModelMapper modelMapper;
	
	public CategoryServiceImpl(CategoriesRepo categoryRepository2, ModelMapper modelMapper2) {
		this.categoriesRepo = categoryRepository2;
		this.modelMapper = modelMapper2;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Categories category = categoriesRepo.save(modelMapper.map(categoryDto, Categories.class));
		return modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(int id, CategoryDto categoryDto) {
		Optional<Categories> categoryOptional = categoriesRepo.findById(id);
		if(categoryOptional.isPresent()) {
			Categories category = categoryOptional.get();
			modelMapper.map(categoryDto, category);
			category = categoriesRepo.save(category);
			return modelMapper.map(category, CategoryDto.class);
		}
		throw new ResourceNotFoundException("Categories not found");
	}

	@Override
	public ResponseEntity<?> deleteCategory(int id) {
		Optional<Categories> optional = categoriesRepo.findById(id);
		if(optional.isPresent()) {
			Categories category = optional.get();
				categoriesRepo.delete(category);
				return ResponseEntity.ok(new MessageResponse("The category deleted successfully"));
			}
		throw new ResourceNotFoundException("Categories is not found");
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Categories> list = categoriesRepo.findAll();
		List<CategoryDto> listDto = new ArrayList<CategoryDto>();
		list.forEach(c -> listDto.add(modelMapper.map(c, CategoryDto.class)));
		return listDto;
	}
}
