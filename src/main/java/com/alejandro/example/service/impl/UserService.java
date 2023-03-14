package com.alejandro.example.service.impl;

import org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.model.entity.UserEntity;
import com.alejandro.example.repository.UserRepository;
import com.alejandro.example.util.mapper.Mapper;

@Service("userService")
public class UserService implements UserDetailsService {
	
	private static final Logger LOG = LogManager.getLogger(UserService.class); 

	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = repository.findByUsername(username);
		if(user == null ) {
			LOG.error("Error: user " + username + " not exist");
			throw new UsernameNotFoundException("Error: user " + username + " not exist");
		}
		return Mapper.mapUserToUserDetail(user);
	}

}
