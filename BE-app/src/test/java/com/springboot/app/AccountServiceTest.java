package com.springboot.app;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.app.dto.PasswordDto;
import com.springboot.app.model.Accounts;
import com.springboot.app.repository.AccountsRepo;
import com.springboot.app.repository.RolesRepo;
import com.springboot.app.serviceimlp.AccountServiceImpl;

public class AccountServiceTest {
	@Autowired
	AccountServiceImpl accountServiceImpl;
	@Autowired
	AccountsRepo accountsRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	Accounts account;
	@Autowired
	PasswordDto passwordDto ;
	@Autowired
    PasswordEncoder encoder;
	@Autowired
    RolesRepo rolesRepo;
	
	private	Accounts initialAccount;
	private Accounts expectedAccount;

	
	@BeforeEach
	public void beforeEach() {
		
		accountsRepo = mock(AccountsRepo.class);
		modelMapper = mock(ModelMapper.class);
		passwordDto = mock(PasswordDto.class);
		account 	= mock(Accounts.class);
		encoder 	= mock(PasswordEncoder.class);
		accountServiceImpl = new AccountServiceImpl(accountsRepo,modelMapper,encoder);
		initialAccount = mock(Accounts.class);
		
		when(accountsRepo.save(initialAccount)).thenReturn(expectedAccount);
		when(modelMapper.map(passwordDto, Accounts.class)).thenReturn(initialAccount);
		
		}
	
	@Test
	public void updatePassword_ShouldReturnHttpOk_WhenDataValid() {
		when(accountsRepo.findById(1)).thenReturn(Optional.of(account));
		ResponseEntity<?> result = accountServiceImpl.updateAccount(1,passwordDto);
		verify(account).setPassword(passwordDto.getPassword());
		assertThat(result.getStatusCode(), is(HttpStatus.OK));
	}

	
}
