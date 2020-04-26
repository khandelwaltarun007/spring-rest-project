/**
 * 
 */
package com.spring.rest.example1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.rest.example1.pojo.entity.Employee;

/**
 * @author tarkhand
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT employees from Employee employees WHERE employees.name = :name")
	public List<Employee> findByEmployeeName(@Param("name") String name);

}