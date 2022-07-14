package com.springboot.app.serviceimlp;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.AccInfoDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.AccInfo;
import com.springboot.app.model.Accounts;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.AccInfoRepo;
import com.springboot.app.repository.AccountsRepo;
import com.springboot.app.service.AccInfoService;

@Component
public class AccInfoServiceImpl implements AccInfoService {

	@Autowired
	AccInfoRepo accInfoRepo;
	@Autowired
	AccountsRepo accountsRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> updateInformation(int id, AccInfoDto informationDto) {
		// TODO Auto-generated method stub
		Optional<AccInfo> optionalInformation = accInfoRepo.findById(id);
		if (!optionalInformation.isPresent()) {
			throw new ResourceNotFoundException("Information not found");
		}
		Optional<Accounts> optionalAccount = accountsRepo.findById(informationDto.getAccount().getId());
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Account not found");
		}

		AccInfo reInformation = optionalInformation.get();
		if (!reInformation.getPhone().equals(informationDto.getPhone())) {
			Optional<AccInfo> optionalPhone = accInfoRepo.findByPhone(informationDto.getPhone());
			if (optionalPhone.isPresent()) {
				throw new IllegalArgumentException("Phone number is already taken");
			}
		}

		modelMapper.map(informationDto, reInformation);
		accInfoRepo.save(reInformation);
		return ResponseEntity.ok(new MessageResponse("Update information successfully !"));
	}

}