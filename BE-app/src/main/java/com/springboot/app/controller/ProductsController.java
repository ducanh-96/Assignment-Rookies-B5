package com.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.ProductDto;
import com.springboot.app.dto.ResponseObject;
import com.springboot.app.service.ProductServices;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsController {

	@Autowired
	private ProductServices productServices;

	@PostMapping("/")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto) {
		return productServices.addProduct(productDto);
	}

	@PutMapping("/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @Valid @RequestBody ProductDto productDto) {

		return productServices.updateProduct(id, productDto);

	}

	@DeleteMapping("/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		return productServices.deleteProduct(id);
	}

	@GetMapping("/")
//	@PreAuthorize("hasAuthority('customer') or hasAuthority('admin')")
	public ResponseEntity<?> getAllProducts() {
		return productServices.getAllProduct();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('customer') or hasAuthority('admin')")
	public ResponseEntity<ResponseObject> getAllProductsByCategory(@PathVariable("id") int id) {
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "List product successfully", productServices.getAllProductbyCategory(id)));

	}
}