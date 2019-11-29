package com.alejandro.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.model.entity.RegionEntity;
import com.alejandro.example.repository.ClientRepository;
import com.alejandro.example.service.interfaces.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	ClientRepository repocitory;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientEntity> findAllClients() {
		return repocitory.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ClientEntity> findAllClients(Pageable pageable) {
		return repocitory.findAll(pageable);
	}

	@Override
	@Transactional
	public ClientEntity save(ClientEntity client) {
		return repocitory.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public ClientEntity findById(Long id) {
		return repocitory.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repocitory.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ClientEntity findByEmail(String email) {
		return repocitory.findByEmail(email);
	}

	@Override
	public List<RegionEntity> getAllRegions() {
		return repocitory.findAllRegions();
	}

}
