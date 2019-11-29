package com.alejandro.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.example.model.entity.RegionEntity;

public interface RegionRepocitory extends JpaRepository<RegionEntity, Long>{

}
