package com.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CartItemDto;
import com.springboot.app.service.CartItemService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartItemService cartItemService;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<?> getCartByIdAccount(@PathVariable("id") Integer id){
		return (ResponseEntity<?>) cartItemService.getCartById(id);
	}
	@PostMapping("/")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<?> addCart(@RequestBody CartItemDto cartItemDto){
		return cartItemService.addCart(cartItemDto);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('customer')")

	public ResponseEntity<?> updateCart(@PathVariable("id")Integer id,@RequestBody CartItemDto cartItemDto){
		return cartItemService.updateCart(id,cartItemDto);
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('customer')")

	public ResponseEntity<?> deleteCart(@PathVariable Integer id){
		return cartItemService.deleteCart(id);
	}

}