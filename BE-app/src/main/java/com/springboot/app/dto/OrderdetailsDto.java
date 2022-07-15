package com.springboot.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderdetailsDto {

	private Integer id;
	private Double price;
	private Integer quantity;

	private ProductDto product;
	private OrdersDto order;

}