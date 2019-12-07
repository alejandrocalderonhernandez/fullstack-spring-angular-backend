package com.alejandro.example.service.interfaces;

import com.alejandro.example.model.entity.UserEntity;

public interface IUserService {
	
	public UserEntity getByUsername( String username );

}
