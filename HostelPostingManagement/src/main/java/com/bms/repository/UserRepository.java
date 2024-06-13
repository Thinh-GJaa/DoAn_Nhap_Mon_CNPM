package com.bms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.Account;
import com.bms.entity.User;

@Controller
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByPhone(String phone);
	Optional<User> findByEmail(String email);
	Optional<User> findByAccount(Account account);
	
	Page<User> findAll(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "delete from saved_post where post_id =:postId and user_id =:userId",nativeQuery = true)
	void deletePosts (@Param("postId") Long postId, @Param("userId") Long userId);
	
//	@Query(value = "select * from product where product_name like %:name% ORDER BY product_id DESC", nativeQuery = true)
	Page<User> findByAccount_usernameContaining(String username, Pageable pageable);
}
