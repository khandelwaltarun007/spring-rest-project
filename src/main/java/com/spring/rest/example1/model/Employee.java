/**
 * 
 */
package com.spring.rest.example1.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

/**
 * @author tarkhand
 *
 */
@Entity
@Table(name = "employee")
public class Employee extends RepresentationModel<Employee> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, message = "Name should contain atleast 2 characters.")
	private String name;

	@Size(min = 2, message = "Designation should contain atleast 2 characters.")
	private String designation;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "user_id")
	private User user;

	public Employee() {
	}

	public Employee(@Size(min = 2, message = "Name should contain atleast 2 characters.") String name,
			@Size(min = 2, message = "Designation should contain atleast 2 characters.") String designation,
			User user) {
		super();
		this.name = name;
		this.designation = designation;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
