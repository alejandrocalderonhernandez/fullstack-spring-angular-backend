package com.alejandro.example.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.alejandro.example.model.dto.ClientDTO;
import com.alejandro.example.model.dto.RegionDTO;
import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.model.entity.RegionEntity;
import com.alejandro.example.model.entity.UserEntity;

public class Mapper {
	
	private Mapper() {
	}
	
	public static ClientDTO mapClient(ClientEntity entity) {
		return new ClientDTO(
				entity.getId(),
				entity.getName(),
				entity.getEmail(), 
				entity.getLastname(), 
				entity.getCreatedAt(), 
				entity.getPhoto(), 
				entity.getRegion()
		);
	}
	
	public static ClientEntity mapClient(ClientDTO dto) {
		return new ClientEntity(
				dto.getId(),
				dto.getName(),
				dto.getEmail(), 
				dto.getLastname(), 
				dto.getCreatedAt(), 
				dto.getPhoto(), 
				dto.getRegion()
		);
	}
	
	public static RegionDTO mapRegion(RegionEntity entity) {
		return new RegionDTO(entity.getIdRegion(), entity.getName(),  entity.getClients());
	}

	public static RegionDTO mapRegion(RegionDTO dto) {
		return new RegionDTO(dto.getIdRegion(), dto.getName(),  dto.getClients());
	}
	
	public static UserDetails mapUserToUserDetail(UserEntity user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map( role -> 
			new SimpleGrantedAuthority(role.getName()
		)).collect(Collectors.toList());
		return new User(user.getUsername(), 
											 user.getPassword(),
											 user.getIsEnabled(),
											 true, true, true,
											 authorities);
	}
	
}
