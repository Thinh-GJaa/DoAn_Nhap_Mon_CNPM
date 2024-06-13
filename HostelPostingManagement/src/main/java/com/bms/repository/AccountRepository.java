package com.bms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bms.entity.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
	public Optional<Account> findByUsername(String username);
}
