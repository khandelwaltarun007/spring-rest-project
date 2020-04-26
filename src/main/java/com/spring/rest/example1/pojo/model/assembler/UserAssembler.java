/**
 * 
 */
package com.spring.rest.example1.pojo.model.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.spring.rest.example1.controller.EmployeeResource;
import com.spring.rest.example1.controller.UserResource;
import com.spring.rest.example1.pojo.entity.User;
import com.spring.rest.example1.pojo.model.EmployeeModel;
import com.spring.rest.example1.pojo.model.UserModel;

/**
 * @author tarkhand
 *
 */
@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	public UserAssembler() {
		super(UserResource.class, UserModel.class);
	}

	@Override
	public UserModel toModel(User entity) {
		UserModel userModel = instantiateModel(entity);
		userModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getByUserId(entity.getId())).withSelfRel());
		userModel.setId(entity.getId());
		userModel.setCreatedBy(entity.getCreatedBy());
		userModel.setCreatedDate(entity.getCreatedDate());
		userModel.setPassword(entity.getPassword());
		userModel.setRole(entity.getRole());
		userModel.setStatus(entity.getStatus());
		userModel.setUpdatedBy(entity.getUpdatedBy());
		userModel.setUpdatedDate(entity.getUpdatedDate());
		userModel.setUsername(entity.getUsername());
		EmployeeModel employeeModel = new EmployeeModel(entity.getEmployee().getId(), entity.getEmployee().getName(),
				entity.getEmployee().getDesignation());
		employeeModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EmployeeResource.class).getEmployeeById(entity.getId()))
				.withSelfRel());
		userModel.setEmployee(employeeModel);
		return userModel;
	}

	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
		CollectionModel<UserModel> userModels = super.toCollectionModel(entities);
		userModels
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class).getUsers()).withSelfRel());
		return userModels;
	}
}
