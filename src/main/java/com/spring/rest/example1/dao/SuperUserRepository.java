package com.spring.rest.example1.dao;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.example1.model.User;

@Repository
public class SuperUserRepository {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	@Transactional
	public void createSuperUser() {
		Optional<User> optionalUser = userRepository.findById(Long.valueOf(1));
		User existingUser = optionalUser.orElse(null);
		if (existingUser == null) {
			User user = new User();
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setUsername("admin");
			user.setRole("ADMIN");
			userRepository.save(user);
		}
	}

}
