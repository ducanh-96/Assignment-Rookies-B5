package com.springboot.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.dto.AccInfoDto;

@Service
public interface AccInfoService {
	public ResponseEntity<?> updateInformation(int id, AccInfoDto informationDto);
}
