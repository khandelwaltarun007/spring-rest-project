package com.spring.rest.example1.service;

import java.util.List;
import java.util.Optional;

import com.spring.rest.example1.model.User;

public interface UserService {
	public Optional<User> getByUserId(Long id);

	public List<User> getUsers();

	public User getByUserName(String userName);

	public User updateUser(User user);

	public User createUser(User user);

	public void deleteUser(Long id);
}
