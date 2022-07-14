package com.springboot.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
public class AccInfoDto {
	
	private Integer id;
	@NotEmpty(message = "Address must not be empty")
    private String address;
	@NotEmpty(message = "Name must not be empty")
    private String name;
	@NotEmpty(message = "phoneNumber name must not be empty")
	@Pattern(regexp = "^0\\d{9}", message = "Phone Number must have 10 number and start with 0")
    private String phone; 
    private AccountDto account;

}
