package com.springboot.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class ProductDto {
	private Integer id;

	private String createDate;

	private String description;

	@NotNull(message = "price must not be null")
	@Min(value = 0, message = "Price >= 0")
	private double price;

	@NotEmpty(message = "name must not be empty")
	private String name;

	@NotNull(message = "quantity must not be null")
	@Min(value = 0, message = "Quantity >= 1")
	private int quantity;

	private String updateDate;

	@NotNull(message = "Category must not be null")
	private CategoryDto category;
}
