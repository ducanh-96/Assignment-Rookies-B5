package com.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CategoryDto;
import com.springboot.app.dto.ResponseObject;
import com.springboot.app.service.CategoryService;

@RestController
@RequestMapping("/admin/category")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ResponseObject> getAllCategories() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "List Category successfully", categoryService.getAllCategory()));
	}
	
	@PostMapping("/")
//	@PreAuthorize("hasAuthority('admin')")
	public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return categoryService.addCategory(categoryDto);
	}

	@PutMapping("/category/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable int id,
			@Valid @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "update category successfully",
				categoryService.updateCategory(id, categoryDto)));
	}

	@DeleteMapping("/category/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable("id") int id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Delete category successsful", categoryService.deleteCategory(id)));
	}
}
