package com.spring.rest.example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.example1.pojo.entity.AuthenticationRequest;
import com.spring.rest.example1.pojo.entity.AuthenticationResponse;
import com.spring.rest.example1.security.util.JwtUtil;
import com.spring.rest.example1.service.MyAppUserDetailService;

@RestController
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyAppUserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/api/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody(required = true) AuthenticationRequest credentials) throws Exception {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		try {
			System.out.println("username : "+username);
			System.out.println("password : "+password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password." + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(username);
		System.out.println("userDetails from authentication resource : "+userDetails.getPassword());
		final String token = jwtUtil.generateToken(userDetails);
		System.out.println(token);
		return new ResponseEntity<>(new AuthenticationResponse(token, jwtUtil.getTokenExpirationDate(token)), HttpStatus.OK);
	}
}
