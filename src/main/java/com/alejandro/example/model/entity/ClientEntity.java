package com.alejandro.example.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name =  "clients")
public class ClientEntity implements Serializable {
	
	private static final long serialVersionUID = -6312545723876431143L;
	@Id
	@Column(name = "id_client", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 15)
	@Column(name = "name_client", length = 15, nullable = false)
	private String name;
	
	@NotEmpty
	@Email
	@Size(min = 10, max = 30)
	@Column(name = "email", length = 30, nullable = false, unique = true)
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 15)
	@Column(name = "last_name", length = 15, nullable = true)
	private String lastname;
	
	@Column(name = "date_creation")
	private LocalDateTime createdAt;
	
	@Column(name = "photo", nullable = true)
	private String photo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_region", nullable=true)
	private RegionEntity region;
	
	public ClientEntity() {}

	public ClientEntity(Long id,  String name,  String email,  String lastname,
			LocalDateTime createdAt, String photo, RegionEntity region) {
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
		if (!(obj instanceof ClientEntity))
			return false;
		ClientEntity other = (ClientEntity) obj;
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
		return "ClientEntity [id=" + id + ", name=" + name + ", email=" + email + ", lastname=" + lastname
				+ ", createdAt=" + createdAt + ", photo=" + photo + ", region=" + region + "]";
	}
	
}
