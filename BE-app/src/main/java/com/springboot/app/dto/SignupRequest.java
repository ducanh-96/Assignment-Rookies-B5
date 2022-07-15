package com.springboot.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest {
	@NotEmpty(message = "Email must not be empty")
	@Size(min = 3, max = 64)
	@Email
	private String email;
	@NotEmpty(message = "Role must not be empty")
	private String address;
	@NotEmpty(message = "Name must not be empty")
	private String name;
	@NotEmpty(message = "Phone must not be empty")
	private String phone;

	@NotBlank
	@Size(min = 5, max = 64)
	private String password;

}
