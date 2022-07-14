package com.springboot.app.dto;

import com.springboot.app.model.Accounts;

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
public class ProductRating {

	private Integer id;

	private String name;

	private String comment;
	
	private int star;

	private Accounts account;

	private ProductDto product;

}