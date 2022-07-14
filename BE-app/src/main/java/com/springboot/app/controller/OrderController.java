package com.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.springboot.app.dto.OrdersDto;
import com.springboot.app.service.AccountServices;
import com.springboot.app.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	AccountServices accountServices;
	@Autowired
	OrderService orderServices;
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('customer') or hasAuthority('admin')")
	public ResponseEntity<?> getOrders(@PathVariable("id") Integer id){
		return accountServices.getOrdersbyId(id);
	}
	@GetMapping("/")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> getAllOrder(){
		return getAllOrder();
	}
	@PostMapping("/")
	public ResponseEntity<?> addOrder(@RequestBody OrdersDto ordersDto){
		return orderServices.addOrder(ordersDto);
	}
	
}
