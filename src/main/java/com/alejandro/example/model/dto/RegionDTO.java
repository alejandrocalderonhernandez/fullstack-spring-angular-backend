package com.alejandro.example.model.dto;

import java.io.Serializable;
import java.util.Set;

import com.alejandro.example.model.entity.ClientEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class RegionDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idRegion;
	private String name;
	@JsonBackReference
	Set<ClientEntity> clients;
	
	public RegionDTO(Long idRegion, String name, Set<ClientEntity> clients) {
		super();
		this.idRegion = idRegion;
		this.name = name;
		this.clients = clients;
	}
	
	public Long getIdRegion() {
		return idRegion;
	}
	
	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<ClientEntity> getClients() {
		return clients;
	}
	
	public void setClients(Set<ClientEntity> clients) {
		this.clients = clients;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((idRegion == null) ? 0 : idRegion.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegionDTO other = (RegionDTO) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (idRegion == null) {
			if (other.idRegion != null)
				return false;
		} else if (!idRegion.equals(other.idRegion))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RegionDTO [idRegion=" + idRegion + ", name=" + name + ", clients=" + clients + "]";
	}

}
