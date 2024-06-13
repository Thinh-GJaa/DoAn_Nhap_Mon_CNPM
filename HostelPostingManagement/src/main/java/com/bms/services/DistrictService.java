package com.bms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.District;
import com.bms.repository.DistrictRepository;

@Service
public class DistrictService {
	@Autowired
	DistrictRepository districtRepository;
	
	public District findById(int id) {
		return districtRepository.findById(id).get();
	}
	
	public List<District> findAll(){
		return (List<District>) districtRepository.findAll();
	}
}
