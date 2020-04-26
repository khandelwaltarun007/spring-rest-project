/**
 * 
 */
package com.spring.rest.example1.pojo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author tarkhand
 *
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, message = "Name should contain atleast 2 characters.")
	@NotBlank(message = "employee name is mandatory.")
	private String name;

	@Size(min = 2, message = "Designation should contain atleast 2 characters.")
	private String designation;

	public Employee() {
	}

	public Employee(@Size(min = 2, message = "Name should contain atleast 2 characters.") String name,
			@Size(min = 2, message = "Designation should contain atleast 2 characters.") String designation) {
		super();
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
