package com.bms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import com.bms.entity.Image;
import com.bms.entity.Post;

@Controller
public interface ImageRepository extends CrudRepository<Image, Long>{
	
	List<Image> findByPost(Post post);
	
} 
