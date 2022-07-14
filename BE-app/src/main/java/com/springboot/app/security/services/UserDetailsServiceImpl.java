package com.springboot.app.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.Accounts;
import com.springboot.app.repository.AccountsRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountsRepo accountR;

	public UserDetailsServiceImpl(AccountsRepo accountR) {
		this.accountR = accountR;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Accounts acc = accountR.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

		return UserDetailsImpl.build(acc);
	}

}
