package com.springboot.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.PasswordDto;
import com.springboot.app.model.Accounts;

@Service
public interface AccountServices {

	public ResponseEntity<?> updateAccount(int id, PasswordDto accountDto);

	public ResponseEntity<?> deleteAccount(int id);

	public List<Accounts> getAllAccount();

	public ResponseEntity<?> getOrdersbyId(Integer id);
}