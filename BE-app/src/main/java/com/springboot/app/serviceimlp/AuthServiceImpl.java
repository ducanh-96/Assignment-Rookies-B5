package com.springboot.app.serviceimlp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springboot.app.dto.LoginRequest;
import com.springboot.app.dto.SignupRequest;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.AccInfo;
import com.springboot.app.model.Accounts;
import com.springboot.app.model.Roles;
import com.springboot.app.reponse.JwtResponse;
import com.springboot.app.reponse.MessageResponse;
import com.springboot.app.repository.AccInfoRepo;
import com.springboot.app.repository.AccountsRepo;
import com.springboot.app.repository.RolesRepo;
import com.springboot.app.security.jwt.JwtUtils;
import com.springboot.app.security.services.UserDetailsImpl;
import com.springboot.app.service.AuthService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthServiceImpl implements AuthService {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AccountsRepo accountsRepo;
	@Autowired
	RolesRepo rolesRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AccInfoRepo accInfoRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0)));
	}

	@Override
	public ResponseEntity<?> signup(SignupRequest signUpRequest) {
		// TODO Auto-generated method stub
		Optional<Accounts> optionalAcc = accountsRepo.findByEmail(signUpRequest.getEmail());
		if (((Optional<?>) optionalAcc).isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's Accounts
		Accounts user = new Accounts(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		String name = signUpRequest.getName();

		Optional<Roles> optional = rolesRepo.findByName(name);
		if (optional.isPresent()) {
			Roles r = optional.get();
			r.setName(String.valueOf("USER"));
			user.setRole(r);
			user = accountsRepo.save(user);

			AccInfo accinfo = modelMapper.map(signUpRequest, AccInfo.class);
			accinfo.setAccount(user);
			accInfoRepo.save(accinfo);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		throw new ResourceNotFoundException("Role not found");
	}
}