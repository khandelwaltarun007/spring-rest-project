/**
 * 
 */
package com.spring.rest.example1.service;

import java.util.List;
import java.util.Optional;

import com.spring.rest.example1.model.Employee;

/**
 * @author tarkhand
 *
 */
public interface EmployeeService {
	public Optional<Employee> getEmployeeById(Long id);

	public List<Employee> getEmployees();

	public List<Employee> getEmployeeByName(String name);

	public Employee updateEmployee(Employee employee);

	public Employee createEmployee(Employee employee);

	public void deleteEmployee(Long id);
}
