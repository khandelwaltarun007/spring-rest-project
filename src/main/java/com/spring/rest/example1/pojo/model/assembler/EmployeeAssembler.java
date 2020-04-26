package com.spring.rest.example1.pojo.model.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.spring.rest.example1.controller.EmployeeResource;
import com.spring.rest.example1.pojo.entity.Employee;
import com.spring.rest.example1.pojo.model.EmployeeModel;

@Component
public class EmployeeAssembler extends RepresentationModelAssemblerSupport<Employee, EmployeeModel> {

	public EmployeeAssembler() {
		super(EmployeeResource.class, EmployeeModel.class);
	}

	@Override
	public EmployeeModel toModel(Employee entity) {
		EmployeeModel employeeModel = instantiateModel(entity);
		employeeModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EmployeeResource.class).getEmployeeById(entity.getId()))
				.withSelfRel());
		employeeModel.setId(entity.getId());
		employeeModel.setName(entity.getName());
		employeeModel.setDesignation(entity.getDesignation());
		return employeeModel;
	}

	@Override
	public CollectionModel<EmployeeModel> toCollectionModel(Iterable<? extends Employee> entities) {
		CollectionModel<EmployeeModel> employeeModels = super.toCollectionModel(entities);
		employeeModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeResource.class).getEmployees())
				.withSelfRel());
		return employeeModels;
	}

}
