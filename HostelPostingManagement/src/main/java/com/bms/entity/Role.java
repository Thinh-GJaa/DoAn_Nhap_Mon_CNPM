package com.bms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role {
	@Id
	private String name;

	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<Account> accounts = new ArrayList<Account>();

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Role(String name, List<Account> accounts) {
		super();
		this.name = name;
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Role() {
	}
}
