package com.spring.rest.example1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.rest.example1.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT user from User user WHERE user.username = :username")
	public User findByUsername(@Param("username") String userName);
	
	@Query("SELECT user from User user WHERE user.id= :id")
	public User findUserStatusById(@Param("id") String id);

}
