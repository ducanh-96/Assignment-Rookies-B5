package com.springboot.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Categories;
import com.springboot.app.repository.CategoriesRepo;

@RestController
@RequestMapping("/api/category")
public class CategoriesController {
	@Autowired
    private CategoriesRepo categoriesRepo;

    @GetMapping("/")
    public List<Categories> getAllCategories() {
        return categoriesRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoriesById(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException {
    	Categories categories = categoriesRepo.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
        return ResponseEntity.ok().body(categories);
    }
    
    @PostMapping("/")
    public Categories createCategories(@Valid @RequestBody Categories categories) {
        return categoriesRepo.save(categories);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable(value = "id") Long id,
         @Valid @RequestBody Categories categoriesDetails) throws ResourceNotFoundException {
    	Categories categories = categoriesRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));

    	categories.setName(categoriesDetails.getName());
    	categories.setSlug(categoriesDetails.getSlug());
    	categories.setIsenabled(categoriesDetails.getIsenabled());
        final Categories updatedCategories = categoriesRepo.save(categories);
        return ResponseEntity.ok(updatedCategories);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategories(@PathVariable(value = "id") Long id)
         throws ResourceNotFoundException {
    	Categories categories = categoriesRepo.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));

    	categoriesRepo.delete(categories);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
