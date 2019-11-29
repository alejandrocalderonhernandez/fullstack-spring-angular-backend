package com.alejandro.example.util.mapper;

import org.springframework.stereotype.Component;

import com.alejandro.example.model.dto.ClientDTO;
import com.alejandro.example.model.dto.RegionDTO;
import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.model.entity.RegionEntity;

@Component
public class Mapper {
	
	public static ClientDTO mapClient(ClientEntity entity) {
		return new ClientDTO(
				entity.getIdClient(),
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
	
}
