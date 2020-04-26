package com.spring.rest.example1.pojo.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

@Relation(collectionRelation = "users")
@JsonRootName(value = "user")
@JsonInclude(Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {

	private Long id;

	private String username;

	private String role;

	@JsonIgnore
	private String password;

	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedDate;

	private String createdBy;

	private String updatedBy;

	private EmployeeModel employee;

	public UserModel() {
	}

	public UserModel(Long id, String username, String role, String password, String status, Date createdDate,
			Date updatedDate, String createdBy, String updatedBy, EmployeeModel employee) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.employee = employee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}
}
