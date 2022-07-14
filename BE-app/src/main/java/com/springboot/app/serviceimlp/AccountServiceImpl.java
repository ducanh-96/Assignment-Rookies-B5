package com.springboot.app.serviceimlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.OrdersDto;
import com.springboot.app.dto.PasswordDto;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Accounts;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.AccountsRepo;
import com.springboot.app.service.AccountServices;

@Component
public class AccountServiceImpl implements AccountServices {
	@Autowired
	AccountsRepo accountRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder encoder;

	public AccountServiceImpl(AccountsRepo accountRepository2, ModelMapper modelMapper2,
			PasswordEncoder encoder2) {
		this.accountRepo = accountRepository2;
		this.modelMapper = modelMapper2;
		this.encoder = encoder2;
	}

	@Override
	public ResponseEntity<?> updateAccount(int id, PasswordDto accountDto){
		// TODO Auto-generated method stub
		Optional<Accounts> optional = accountRepo.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Accounts is not found");
		}
		Accounts Accounts = optional.get();
		Accounts.setPassword(encoder.encode(accountDto.getPassword()));
		accountRepo.save(Accounts);

		return ResponseEntity.ok(new MessageResponse("Update password successfully"));
	}

	@Override
	public ResponseEntity<?> deleteAccount(int id) {
		Optional<Accounts> optional = accountRepo.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		Accounts Accounts = optional.get();
		accountRepo.delete(Accounts);
		return ResponseEntity.ok(new MessageResponse("The Accounts delete successfully"));
	}

	@Override
	public List<Accounts> getAllAccount() {
		return accountRepo.findAll();
	}

	@Override
	public ResponseEntity<?> getOrdersbyId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Accounts> optional = accountRepo.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		Accounts Accounts = optional.get();
		if (Accounts.getOrders().size() == 0) {
			return ResponseEntity.ok("Accounts don't have orders");
		}
		List<OrdersDto> list = new ArrayList<OrdersDto>();
		Accounts.getOrders().forEach(order -> list.add(modelMapper.map(order, OrdersDto.class)));
		return ResponseEntity.ok(list);
	}

	
}
