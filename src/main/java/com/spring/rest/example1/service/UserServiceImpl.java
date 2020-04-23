/**
 * 
 */
package com.spring.rest.example1.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.rest.example1.dao.UserRepository;
import com.spring.rest.example1.model.User;

/**
 * @author tarkhand
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> getByUserId(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public User updateUser(User user) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = ((User)principal).getUsername();
		user.setUpdatedBy(userName);
		user.setUpdatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public User createUser(User user) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = ((UserDetails)principal).getUsername();
		System.out.println("#####################################userName:"+userName);
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setCreatedBy(userName);
		user.setUpdatedBy(userName);
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
