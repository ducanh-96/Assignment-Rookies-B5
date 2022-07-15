package com.springboot.app.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.CartItemDto;

@Service
public interface CartItemService {
	public Collection<CartItemDto> getCartById(Integer id);

	public ResponseEntity<?> addCart(CartItemDto cartItemDto);

	public ResponseEntity<?> updateCart(Integer id,CartItemDto cartItemDto);

	public ResponseEntity<?> deleteCart(Integer id);
}