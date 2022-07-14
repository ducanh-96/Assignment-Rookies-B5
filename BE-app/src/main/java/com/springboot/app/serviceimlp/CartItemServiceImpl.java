package com.springboot.app.serviceimlp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.CartItemDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Accounts;
import com.springboot.app.model.CartItem;
import com.springboot.app.model.Products;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.AccountsRepo;
import com.springboot.app.repository.CartItemRepo;
import com.springboot.app.repository.ProductsRepo;
import com.springboot.app.service.CartItemService;
import com.springboot.app.service.ProductServices;

@Component
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	AccountsRepo accountRepo;
	@Autowired
	ProductServices productServices;
	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	ProductsRepo productRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Collection<CartItemDto> getCartById(Integer id) {
		Optional<Accounts> optionalAccount = accountRepo.findById(id);
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		List<CartItem> list = accountRepo.findCartById(id);
		List<CartItemDto> dto = new ArrayList<CartItemDto>();
		list.forEach(c -> dto.add(modelMapper.map(c, CartItemDto.class)));
		return dto;
	}

	@Override
	public ResponseEntity<?> addCart(CartItemDto cartItemDto) {
		// TODO Auto-generated method stub
		Optional<Products> optionalProduct = productRepo.findById(cartItemDto.getProduct().getId());
		if (!optionalProduct.isPresent()) {
			throw new ResourceNotFoundException("Product not found");
		}
		Optional<Accounts> optionalAccount = accountRepo.findById(cartItemDto.getAccount().getId());
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}		
			cartItemRepo.save(modelMapper.map(cartItemDto, CartItem.class));

		return ResponseEntity.ok(new MessageResponse("Cart is added successfully"));

	}

	@Override
	public ResponseEntity<?> updateCart(Integer id,CartItemDto cartItemDto) {
		Optional<Products> optionalProduct = productRepo.findById(cartItemDto.getProduct().getId());
		if (!optionalProduct.isPresent()) {
			throw new ResourceNotFoundException("Product not found");
		}
		Optional<Accounts> optionalAccount = accountRepo.findById(cartItemDto.getAccount().getId());
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		Optional<CartItem> optionalCart = cartItemRepo.findById(id);
		if (optionalCart.isPresent()) {
			CartItem cart = optionalCart.get();
			if (cart.getQuantity() == 0) {
				cartItemRepo.delete(cart);
			}
			cart.setQuantity(cartItemDto.getQuantity());
			cartItemRepo.save(cart);
			return ResponseEntity.ok(new MessageResponse("Cart is updated successfully"));
		}
		throw new ResourceNotFoundException("Cart not found");
	}

	@Override
	public ResponseEntity<?> deleteCart(Integer id) {
		Optional<Accounts> optionalAccount = accountRepo.findById(id);
		if(!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		List<CartItem> list = cartItemRepo.findByIdAccount(id);
		cartItemRepo.deleteAll(list);
		return ResponseEntity.ok(new MessageResponse("Deleted successfully"));
	}

}
