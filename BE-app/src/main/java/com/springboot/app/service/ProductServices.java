package com.springboot.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.ProductDto;

@Service
public interface ProductServices {

	ResponseEntity<?> addProduct(ProductDto productDto);
	
	ResponseEntity<?> updateProduct(int id , ProductDto productDto);

	ResponseEntity<?> deleteProduct(int id);
	
	 ResponseEntity<?> getAllProduct();
	
	 List<ProductDto> getAllProductbyCategory(int id);

	 ProductDto findByIdProduct(int id);
}
