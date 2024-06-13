package com.bms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bms.entity.Account;
import com.bms.entity.Constants;
import com.bms.entity.User;
import com.bms.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public Optional<User> findByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> findByAccount(Account account) {
		return userRepository.findByAccount(account);
	}
	
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public boolean insertUser(User user) {
		return userRepository.save(user) != null;
	}
	
	public Page<User> listAll(int pageNum, String keyword) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		
		if(keyword != null && !keyword.isBlank()) {
			System.out.println("chay vao day r");
			return userRepository.findByAccount_usernameContaining(keyword, pageable);
		}
		return userRepository.findAll(pageable);
	}
	
	public void deleteSaveList(long postId, long userId) {
		 userRepository.deletePosts(postId, userId);
	}
	
}
