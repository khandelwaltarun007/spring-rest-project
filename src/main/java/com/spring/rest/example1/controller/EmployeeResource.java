package com.spring.rest.example1.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.example1.exception.EntityNotFoundException;
import com.spring.rest.example1.model.Employee;
import com.spring.rest.example1.model.EntityList;
import com.spring.rest.example1.service.EmployeeService;

/**
 * 
 * @author tarkhand
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EntityList<Employee> employeeList;
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ADMIN')")
	public EntityModel<EntityList<Employee>> getEmployees() throws NoSuchMethodException, SecurityException{
		employeeList.setEntityList(employeeService.getEmployees());
		EntityModel<EntityList<Employee>> entityModel = new EntityModel<>(employeeList);
		for(Employee employee : employeeList.getEntityList()) {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(EmployeeResource.class).slash(employee.getId());
			employee.add(linkTo.withSelfRel());
		}
		return entityModel;
		 
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public EntityModel<Employee> getEmployeeById(@PathVariable("id") Long id) throws NoSuchMethodException, SecurityException {
		Employee employee = employeeService.getEmployeeById(id)
				.orElseThrow(()-> new EntityNotFoundException(""+id+" does not exist."));
		EntityModel<Employee> entityModel = new EntityModel<Employee>(employee);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeResource.class).getEmployees());
		entityModel.add(linkTo.withRel("employees"));
		linkTo = WebMvcLinkBuilder.linkTo(EmployeeResource.class).slash(employee.getId());
		entityModel.add(linkTo.withSelfRel());
		return entityModel;
	}
	
	@PostMapping(path="/")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
		Employee createdEmployee = employeeService.createEmployee(employee);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
