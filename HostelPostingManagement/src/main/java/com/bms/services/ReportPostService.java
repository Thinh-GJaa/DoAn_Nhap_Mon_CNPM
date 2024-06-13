package com.bms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bms.entity.Constants;
import com.bms.entity.ReportPost;
import com.bms.repository.ReportPostRepository;

@Service
public class ReportPostService {
	@Autowired
	ReportPostRepository reportPostRepository;

	public Page<ReportPost> findAllReport(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, Constants.PAGE_SIZE);
		return reportPostRepository.findByIsSolveFalse(pageable);
	}

	public boolean saveRepostPost(ReportPost reportPost) {
		return reportPostRepository.save(reportPost) != null;
	}
	
	public long postReportAmount() {
		return reportPostRepository.countByIsSolveFalse();
	}
	
	public long postReportAmountofMonth(String monthStart, String monthEnd) {
		return reportPostRepository.countByCreatedDateBetween(monthStart,monthEnd);
	}
	
	public ReportPost findById(String id) {
		return reportPostRepository.findById(id);
	}
}
