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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "app_roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -7921999075203523113L;
	
	@Id
	@Column(name = "id_role", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	@Column(name = "role_name", length = 255)
	@NotEmpty
	private String name;
	@Transient
	@ManyToMany(mappedBy = "roles",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserEntity> users;
	
	public Long getIdRole() {
		return idRole;
	}
	
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<UserEntity> getUsers() {
		return users;
	}
	
	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		RoleEntity other = (RoleEntity) obj;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleEntity [idRole=" + idRole + ", name=" + name + ", users=" + users + "]";
	}

}
