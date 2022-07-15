package com.springboot.app.serviceimlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.ProductDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Categories;
import com.springboot.app.model.Products;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.CategoriesRepo;
import com.springboot.app.repository.ProductsRepo;
import com.springboot.app.service.ProductServices;

@Component
public class ProductServiceImpl implements ProductServices {
	@Autowired
	ProductsRepo productsRepo;
	@Autowired
	CategoriesRepo categoriesRepo;

	private ModelMapper modelMapper;

	public ProductServiceImpl(ProductsRepo productsRepo2, CategoriesRepo categoriesRepo2,
			ModelMapper modelMapper) {
		this.productsRepo = productsRepo2;
		this.categoriesRepo = categoriesRepo2;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<?> addProduct(ProductDto productDto) {
		Optional<Categories> optionalCategory = categoriesRepo.findById(productDto.getCategory().getId());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Categories not found"));
		}

		productsRepo.save(modelMapper.map(productDto, Products.class));
		return ResponseEntity.ok(new MessageResponse("Add new Products successfully"));
	}

	@Override
	public ResponseEntity<?> updateProduct(int id, ProductDto productDto) {
		Optional<Products> optionalProduct = productsRepo.findById(id);
		if (!optionalProduct.isPresent()) {
			throw new ResourceNotFoundException("Products not found");
		}
		Optional<Categories> optionalCategory = categoriesRepo.findById(productDto.getCategory().getId());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Categories not found"));
		}

		Products product = optionalProduct.get();
		product.setCategory(null);
		modelMapper.map(productDto, product);
		product = productsRepo.save(product);
		return ResponseEntity.ok(new MessageResponse("Update Products successfully"));
	}

	@Override
	public ResponseEntity<?> deleteProduct(int id) {
		Optional<Products> optional = productsRepo.findById(id);
		if (optional.isPresent()) {
			Products product = optional.get();
			productsRepo.delete(product);
			return ResponseEntity.ok(new MessageResponse("The book deleted successfully"));
		}
		throw new ResourceNotFoundException("Products is not found");
	}

	@Override
	public ResponseEntity<?> getAllProduct() {
		List<Products> list = productsRepo.findAll();
		List<ProductDto> dto = new ArrayList<ProductDto>();
		list.forEach(b -> dto.add(modelMapper.map(b, ProductDto.class)));
		return ResponseEntity.ok(dto);
	}

	@Override
	public List<ProductDto> getAllProductbyCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto findByIdProduct(int id) {
		Optional<Products> optional = productsRepo.findById(id);
		if (optional.isPresent()) {
			Products product = optional.get();
			return modelMapper.map(product, ProductDto.class);
		}
		throw new ResourceNotFoundException("Products not found");
	}

}