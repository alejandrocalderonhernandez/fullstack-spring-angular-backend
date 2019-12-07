package com.alejandro.example.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alejandro.example.model.entity.RegionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ClientDTO  implements Serializable{
	
	private static final long serialVersionUID = -6312545730879431143L;

	private Long id;
	private String name;
	private String email;
	private String lastname;
	private LocalDateTime createdAt;
	private String photo;
	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private RegionEntity region;
	
	public ClientDTO(Long id, String name, String email, String lastname, LocalDateTime createdAt, String photo,
			RegionEntity region) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.lastname = lastname;
		this.createdAt = createdAt;
		this.photo = photo;
		this.region = region;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public RegionEntity getRegion() {
		return region;
	}
	
	public void setRegion(RegionEntity region) {
		this.region = region;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		ClientDTO other = (ClientDTO) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", name=" + name + ", email=" + email + ", lastname=" + lastname + ", createdAt="
				+ createdAt + ", photo=" + photo + ", region=" + region + "]";
	}
	
}
