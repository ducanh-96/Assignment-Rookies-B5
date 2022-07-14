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
import com.springboot.app.dto.OrdersDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.OrderDetails;
import com.springboot.app.model.Orders;
import com.springboot.app.model.Products;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.OrderDetailsRepo;
import com.springboot.app.repository.OrderRepo;
import com.springboot.app.service.CartItemService;
import com.springboot.app.service.OrderService;
import com.springboot.app.service.ProductServices;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo orderRepo;
	@Autowired
	OrderDetailsRepo orderDetailsRepo;
	@Autowired
	ProductServices productServices;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CartItemService cartItemService;

	@Override
	public OrdersDto findById(int id) {
		Optional<Orders> optional = orderRepo.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Order not found");
		}
		Orders order = optional.get();
		return modelMapper.map(order, OrdersDto.class);
	}

	@Override
	public ResponseEntity<?> getAllOrder() {
		List<Orders> list = orderRepo.findAll();
		List<OrdersDto> listDto = new ArrayList<OrdersDto>();
		list.forEach(s -> listDto.add(modelMapper.map(s, OrdersDto.class)));

		return ResponseEntity.ok(listDto);
	}

	@Override
	public ResponseEntity<?> addOrder(OrdersDto ordersDto) {
		Collection<CartItemDto> cartItems = cartItemService.getCartById(ordersDto.getAccount().getId());		
		if (cartItems.isEmpty()) {
			throw new ResourceNotFoundException("The order was not added successfully");
		}
		Orders orders = orderRepo.save(modelMapper.map(ordersDto, Orders.class));
		List<OrderDetails> listOrderDetails = new ArrayList<>();
		for (CartItemDto cart : cartItems) {
			OrderDetails orderdetails = new OrderDetails();
			orderdetails.setQuantity(cart.getQuantity());
			orderdetails.setProduct(modelMapper.map(cart.getProduct(), Products.class));
			orderdetails.setOrder(orders);
			listOrderDetails.add(orderdetails);
//		OrderdetailsDto orderDetail = new OrderdetailsDto();
//		orderDetail.setQuantity(cart.getQuantity());
//		orderDetail.setOrder(ordersDto);
//		orderDetail.setProduct(productServices.findByIdProduct(cart.getProduct().getIdProduct()));			
//		listOrderDetails.add(modelMapper.map(orderDetail, OrderDetails.class));			
		}
		orderDetailsRepo.saveAll(listOrderDetails);
		cartItemService.deleteCart(ordersDto.getAccount().getId());
		return ResponseEntity.ok(new MessageResponse("The order was added successfully"));
	}
	
}