package com.bms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

}
