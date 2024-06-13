package com.bms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.ReportPost;
import com.bms.entity.ReportPostId;

@Repository
public interface ReportPostRepository extends JpaRepository<ReportPost, ReportPostId>{
	Page<ReportPost> findByIsSolveFalse(Pageable pageable);
	
	long countByCreatedDateBetween(String monthStart, String monthEnd);
	
	long countByIsSolveFalse();
	
	ReportPost findById(String id);
}
