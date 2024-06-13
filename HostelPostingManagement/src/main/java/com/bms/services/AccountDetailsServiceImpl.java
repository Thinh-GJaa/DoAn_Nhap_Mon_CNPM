package com.bms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bms.dao.AccountDetailsDTO;
import com.bms.entity.Account;
import com.bms.repository.AccountRepository;

@Service("accountService")
public class AccountDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Account account  = accountRepository.findById(username).get();
		if (account == null) {
			throw new LockedException("User not found");
		}
		
		if(account.isBlock()) {
			throw new LockedException("Tài khoản đã tạm khóa, vui lòng liên hệ với chúng tôi!");
		}

		return new AccountDetailsDTO(account);
	}
	
}
