package com.alejandro.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.model.entity.RegionEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
	@Query("from RegionEntity")
	public List<RegionEntity> findAllRegions();
	public  ClientEntity  findByEmail(String email);
	
}
  