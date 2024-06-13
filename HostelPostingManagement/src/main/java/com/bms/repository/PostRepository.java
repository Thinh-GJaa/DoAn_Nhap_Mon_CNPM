package com.bms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.Category;
import com.bms.entity.District;
import com.bms.entity.Post;
import com.bms.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// common

	Page<Post> findByIsApproveTrueAndIsActiveTrueOrderByIdDesc(Pageable pageable);

	Page<Post> findByDistrictAndCategoryAndAreaBetweenAndPriceBetweenAndIsApproveTrueAndIsActiveTrueOrderByIdDesc(District district,
			Category category, Double startArea, Double endArea, Double startPrice, Double endPrice, Pageable pageable);

	// admin

	// list all post is wait to approve
	Page<Post> findByIsApproveFalseAndIsHiddenFalseOrderByIdDesc(Pageable pageable);

	// list all post is block/report 
	Page<Post> findByIsHiddenTrueOrderByIdDesc(Pageable pageable);

	// count all post wait to approve
	long countByIsApproveFalseAndIsHiddenFalse();

	// user seller

	// list post of user is wait to approve
	Page<Post> findByUserAndIsApproveFalseAndIsHiddenFalseOrderByIdDesc(User user, Pageable pageable);

	// list post of user is approved
	Page<Post> findByUserAndIsApproveTrueOrderByIdDesc(User user, Pageable pageable);

	// list post of user is block
	Page<Post> findByUserAndIsHiddenTrueOrderByIdDesc(User user, Pageable pageable);

	// list post of user
	Page<Post> findByUserOrderByIdDesc(User user, Pageable pageable);

	// count amount post of user
	long countByUser(User user);

	// count post of user wait to approve
	long countByUserAndIsApproveFalseAndIsHiddenFalse(User user);

	// count post of user is approved
	long countByUserAndIsApproveTrueAndIsHiddenFalse(User user);

	// count amount post block of user
	long countByUserAndIsHiddenTrue(User user);
	
	List<Post> findByCreatedDateBetweenOrderByIdDesc(String monthStart, String monthEnd);
	
	long countByCreatedDateBetween(String monthStart, String monthEnd);
	
	long countByCreatedDateBetweenAndIsApproveTrue(String monthStart, String monthEnd);
}
