package com.springboot.app.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "HomeControllerOfAdmin")
@RequestMapping("/admin")
public class HomeController {

	// GET HTTP Method
	//http://localhost:8080/admin
	@GetMapping({"/", ""})
	public String AdminIndex() {
		return "Admin";
	}
	
}
