package com.spring.rest.example1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.example1.exception.EntityNotFoundException;
import com.spring.rest.example1.pojo.entity.User;
import com.spring.rest.example1.pojo.model.UserModel;
import com.spring.rest.example1.pojo.model.assembler.UserAssembler;
import com.spring.rest.example1.service.UserService;

/**
 * 
 * @author tarkhand
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAssembler userAssembler;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(userAssembler.toModel(createdUser),HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<CollectionModel<UserModel>> getUsers() {
		List<User> users = userService.getUsers();
		return new ResponseEntity<>(userAssembler.toCollectionModel(users), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	public ResponseEntity<UserModel> getByUserId(@PathVariable("id") Long id) {
		return userService.getByUserId(id)
				.map(userAssembler::toModel).map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException("User id : " + id + " not found."));
	}
}
