package com.alejandro.example.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "region")
public class RegionEntity implements Serializable{
	
	private static final long serialVersionUID = 4378023892208582094L;
	
	@Id
	@Column(name = "id_region", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRegion;
	
	@NotEmpty
	@Size(min = 2, max = 20)
	@Column(name = "name_region", length = 20, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "region", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	Set<ClientEntity> clients;
	
	public RegionEntity() {}

	public RegionEntity(Long idRegion, @NotEmpty @Size(min = 2, max = 20) String name, Set<ClientEntity> clients) {
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
		if (!(obj instanceof RegionEntity))
			return false;
		RegionEntity other = (RegionEntity) obj;
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
		return "RegionEntity [idRegion=" + idRegion + ", name=" + name + ", clients=" + clients + "]";
	}

}
