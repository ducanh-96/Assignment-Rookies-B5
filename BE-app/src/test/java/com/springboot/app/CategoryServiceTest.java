package com.springboot.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Categories;
import com.springboot.app.repository.CategoriesRepo;
import com.springboot.app.serviceimlp.CategoryServiceImpl;

public class CategoryServiceTest {
	CategoriesRepo categoriesRepo;
	CategoryServiceImpl categoryServiceImpl;
	ModelMapper modelMapper;
	CategoryDto categoryDto;
	Categories category;
	
	@BeforeEach
	void beforeEach() {
		categoriesRepo = mock(CategoriesRepo.class);
		modelMapper = mock(ModelMapper.class);
		categoryServiceImpl = new CategoryServiceImpl(categoriesRepo,modelMapper);
		categoryDto = mock(CategoryDto.class);
		category = mock(Categories.class);
		
		when(categoriesRepo.save(category)).thenReturn(category);
		when(modelMapper.map(categoryDto,Categories.class)).thenReturn(category);
	}
	
	@Test
	void getCategory_ShouldReturnList_WhenDataValid() {
		// 1. create mock data
		List<Categories> list = new ArrayList<>();
		list.add(category);
		
		
		when(categoriesRepo.findAll()).thenReturn(list);
		
		List<CategoryDto> result = categoryServiceImpl.getAllCategory();
		
		assertThat(result.size(), is(list.size()));
	}
	@Test
	void addCategory_ShouldReturnCategoryDto_WhenDataValid() {
		when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
		CategoryDto result = categoryServiceImpl.addCategory(categoryDto);		
		assertThat(result, is(categoryDto));
	}
	
	@Test
	void updateCategory_ShouldReturnCategoryDto_WhenDataValid() {
		when(categoriesRepo.findById(1)).thenReturn(Optional.of(category));
		when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
		CategoryDto result = categoryServiceImpl.updateCategory(1, categoryDto);
		assertThat(result, is(categoryDto));
	}
	@Test
	void updateCategory_ThrowNotFoundException_WhenCategoryNotFound() {
		when(categoriesRepo.findById(1)).thenReturn(Optional.empty());
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImpl.updateCategory(1, categoryDto));
        assertThat(exception.getMessage(), is("Categories not found"));
	}
	
	@Test
	void deleteCategory_ShouldReturnStatusOk_WhenDataValid() {
		when(categoriesRepo.findById(1)).thenReturn(Optional.of(category));
		when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
		ResponseEntity<?> result = categoryServiceImpl.deleteCategory(1);
		assertThat(result.getStatusCode(), is(HttpStatus.OK));
	}
	@Test
	void deleteCategory_ThrowNotFoundException_WhenCategoryNotFound() {	
		when(categoriesRepo.findById(1)).thenReturn(Optional.empty());
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImpl.deleteCategory(1));
        assertThat(exception.getMessage(), is("Categories is not found"));
	}
	
}


