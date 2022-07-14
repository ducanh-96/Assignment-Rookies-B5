package com.springboot.app.dto;

import javax.validation.constraints.Min;

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
public class CartItemDto  {


    private Integer id;

	@Min(value = 1, message = "Quantity >= 1")
	private int quantity;

	private AccountDto account;

    private ProductCartDto product;

}
