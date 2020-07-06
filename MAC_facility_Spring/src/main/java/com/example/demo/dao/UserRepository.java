package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Users;

import antlr.collections.List;

public interface UserRepository extends CrudRepository<Users, Integer> {

	Users findByUsername(String username);

	Users getOne(Integer id);
	
}
