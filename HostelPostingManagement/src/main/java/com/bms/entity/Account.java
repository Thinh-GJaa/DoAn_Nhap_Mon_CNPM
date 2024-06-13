package com.bms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table
public class Account {

	@Id
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private boolean block;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_name"))
	private Set<Role> roles = new HashSet<Role>();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public Account() {
		
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Account(String username, String password, boolean block, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.block = block;
		this.roles = roles;
	}
	
	
}
