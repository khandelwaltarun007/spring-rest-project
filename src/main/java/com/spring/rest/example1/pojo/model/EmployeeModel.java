/**
 * 
 */
package com.spring.rest.example1.pojo.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author tarkhand
 *
 */
@Relation(collectionRelation = "employees")
@JsonRootName(value = "employee")
@JsonInclude(Include.NON_NULL)
public class EmployeeModel extends RepresentationModel<EmployeeModel> {

	private Long id;

	private String name;

	private String designation;

	public EmployeeModel() {
	}

	public EmployeeModel(Long id, String name, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
