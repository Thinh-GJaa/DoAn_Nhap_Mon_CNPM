package com.bms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bms.entity.Category;
import com.bms.entity.Constants;
import com.bms.entity.District;
import com.bms.entity.Post;
import com.bms.entity.User;
import com.bms.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	
	public Page<Post> findAllOrderByDesc(int pageNum,Category category, District district
			,Double startArea, Double endArea, Double startPrice, Double endPrice){
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		
		
		if(district!=null) {
			return postRepository.findByDistrictAndCategoryAndAreaBetweenAndPriceBetweenAndIsApproveTrueAndIsActiveTrueOrderByIdDesc(district,category,  startArea, endArea, startPrice, endPrice, pageable);
		}
		return postRepository.findByIsApproveTrueAndIsActiveTrueOrderByIdDesc(pageable);
	}
	
	public boolean insertPost(Post post) {
		return postRepository.save(post)!=null;
	}
	
	public Post findById(long id) {
		return postRepository.findById(id).get();
	}
	
	public Page<Post> findAllNonApprovePost(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByIsApproveFalseAndIsHiddenFalseOrderByIdDesc(pageable);
	}
	
	public Page<Post> findAllHiddenPost(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByIsHiddenTrueOrderByIdDesc(pageable);
	}
	
	public Page<Post> findWaitApproveUserPost(int pageNum, User user) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByUserAndIsApproveFalseAndIsHiddenFalseOrderByIdDesc(user, pageable);
	}
	
	public Page<Post> findApproveUserPost(int pageNum, User user) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByUserAndIsApproveTrueOrderByIdDesc(user, pageable);
	}
	
	public Page<Post> findHiddenUserPost(int pageNum, User user) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByUserAndIsHiddenTrueOrderByIdDesc(user, pageable);
	}
	
	public Page<Post> findbyUser(int pageNum, User user) {
		Pageable pageable = PageRequest.of(pageNum-1, Constants.PAGE_SIZE);
		return postRepository.findByUserOrderByIdDesc(user, pageable);
	}
	
	public long counAllWaitApprovePost() {
		return postRepository.countByIsApproveFalseAndIsHiddenFalse();
	}
	
	public long countAmountPostOfUser(User user) {
		return postRepository.countByUser(user);
	}
	
	public long countAmountPostWaitApproveOfUser(User user) {
		return postRepository.countByUserAndIsApproveFalseAndIsHiddenFalse(user);
	}
	
	public long countAmountPostApprovedOfUser(User user) {
		return postRepository.countByUserAndIsApproveTrueAndIsHiddenFalse(user);
	}
	
	public long countAmountPostBlockOfUser(User user) {
		return postRepository.countByUserAndIsHiddenTrue(user);
	}
	
	public List<Post> statisticByMonth(String monthStart, String monthEnd){
		return postRepository.findByCreatedDateBetweenOrderByIdDesc( monthStart, monthEnd);
	}
	
	public long countPostByMonth(String monthStart, String monthEnd){
		return postRepository.countByCreatedDateBetween( monthStart, monthEnd);
	}
	
	public long countPostApproveByMonth(String monthStart, String monthEnd){
		return postRepository.countByCreatedDateBetweenAndIsApproveTrue( monthStart, monthEnd);
	}
}
