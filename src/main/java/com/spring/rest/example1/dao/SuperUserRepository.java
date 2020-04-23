package com.spring.rest.example1.dao;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.example1.model.Employee;
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
			Employee employee = new Employee();
			employee.setName("Admin");
			employee.setDesignation("Super User");
			User user = new User();
			user.setPassword("admin");
			user.setUsername("admin");
			user.setRole("ADMIN");
			user.setStatus("1");
			user.setCreatedBy("System");
			user.setUpdatedBy("System");
			user.setCreatedDate(new Date());
			user.setUpdatedDate(new Date());
			user.setEmployee(employee);
			employee.setUser(user);
			userRepository.save(user);
		}
	}

}
