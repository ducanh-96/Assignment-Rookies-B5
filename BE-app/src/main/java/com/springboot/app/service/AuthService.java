package com.springboot.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.LoginRequest;
import com.springboot.app.dto.SignupRequest;

@Service
public interface AuthService {
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    public ResponseEntity<?>  signup(SignupRequest signupRequest);
}