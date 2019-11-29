package com.alejandro.example.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.model.entity.RegionEntity;

public interface IClientService {
	
	public List<ClientEntity> findAllClients();
	public Page<ClientEntity> findAllClients(Pageable pageable);
	public ClientEntity save(ClientEntity client);
	public ClientEntity findById(Long id);
	public void delete(Long id);
	public  ClientEntity  findByEmail(String email);
	public List<RegionEntity> getAllRegions();
}
