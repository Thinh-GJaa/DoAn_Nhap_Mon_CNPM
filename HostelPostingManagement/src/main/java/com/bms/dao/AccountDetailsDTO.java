package com.bms.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bms.entity.Account;
import com.bms.entity.Role;

public class AccountDetailsDTO implements UserDetails{
	/*
	 * SerialVersionUID is a unique identifier for each class, JVM uses it to
	 * compare the versions of the class ensuring that the same class was used
	 * during Serialization is loaded during Deserialization.
	 */
	private static final long serialVersionUID = 1L;
	
	private Account account;
	
	public AccountDetailsDTO(Account account) {
		this.account = account;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = account.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}
	
	public Account getAccountDetails() {
		return account;
	}
	
	@Override
	public String getPassword() {
		return account.getPassword();
	}
	
	@Override
	public String getUsername() {
		return account.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
