package com.alejandro.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.example.model.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

	public UserEntity findByUsername(String username);
	
}
