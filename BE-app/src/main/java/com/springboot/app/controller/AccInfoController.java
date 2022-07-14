package com.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.AccInfoDto;
import com.springboot.app.service.AccInfoService;

@RestController
@RequestMapping("/information")
public class AccInfoController {
	@Autowired
	AccInfoService accInfoService ;
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('customer') or hasAuthority('admin')")
	public ResponseEntity<?> updateInformation(@PathVariable("id")Integer id,@Valid @RequestBody AccInfoDto accInfoDto){
		return accInfoService.updateInformation(id, accInfoDto);
	}
}
