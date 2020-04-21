package com.spring.rest.example1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.rest.example1.dao.UserRepository;
import com.spring.rest.example1.model.User;

@Service
public class MyAppUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		System.out.println("Users : "+user.toString());
		List<GrantedAuthority> authorities = new ArrayList<>();
		String[] authStrings = user.getRole().split(", ");
		for(String authString : authStrings) {
			authorities.add(new SimpleGrantedAuthority(authString));
		}
		//GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
		return userDetails;
	}

}