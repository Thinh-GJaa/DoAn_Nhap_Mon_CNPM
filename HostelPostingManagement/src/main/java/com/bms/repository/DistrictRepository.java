package com.bms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.District;

@Repository
public interface DistrictRepository extends CrudRepository<District, Integer>{

}
