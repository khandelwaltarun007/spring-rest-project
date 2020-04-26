package com.spring.rest.example1.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.example1.exception.EntityNotFoundException;
import com.spring.rest.example1.pojo.entity.Employee;
import com.spring.rest.example1.pojo.model.EmployeeModel;
import com.spring.rest.example1.pojo.model.assembler.EmployeeAssembler;
import com.spring.rest.example1.service.EmployeeService;

/**
 * 
 * @author tarkhand
 *
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeAssembler employeeAssembler;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CollectionModel<EmployeeModel>> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		return new ResponseEntity<>(employeeAssembler.toCollectionModel(employees), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("id") Long id) {
		return employeeService.getEmployeeById(id).map(employeeAssembler::toModel).map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException("Employee id : " + id + " does not exist."));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee createdEmployee = employeeService.createEmployee(employee);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
