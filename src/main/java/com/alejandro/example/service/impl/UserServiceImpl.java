package com.alejandro.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.example.model.entity.UserEntity;
import com.alejandro.example.repository.UserRepository;
import com.alejandro.example.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository repository;

	@Override
	public UserEntity getByUsername(String username) {
		return this.repository.findByUsername(username);
	}

}
