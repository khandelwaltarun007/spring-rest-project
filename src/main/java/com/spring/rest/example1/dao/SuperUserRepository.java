package com.spring.rest.example1.dao;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.example1.pojo.entity.Employee;
import com.spring.rest.example1.pojo.entity.User;

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
			Employee employee = new Employee("Admin", "Super User");
			User user = new User("admin", "ADMIN", "admin", "1", new Date(), new Date(), "System", "System", employee);
			userRepository.save(user);
		}
	}

}
