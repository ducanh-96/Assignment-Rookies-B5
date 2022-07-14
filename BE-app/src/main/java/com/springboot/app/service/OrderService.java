package com.springboot.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.OrdersDto;

@Service
public interface OrderService {
	public OrdersDto findById(int id);

	public ResponseEntity<?> getAllOrder();

	public ResponseEntity<?> addOrder(OrdersDto dto);
}
