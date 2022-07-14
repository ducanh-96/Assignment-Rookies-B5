package com.springboot.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
	@NotEmpty(message = "Password must not be empty")
	@Size(min = 5, max = 64, message = "The length of the password must be between 5 and 64 characters")
	private String password;

}