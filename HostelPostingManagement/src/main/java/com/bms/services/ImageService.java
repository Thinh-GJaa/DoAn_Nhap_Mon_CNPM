package com.bms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Image;
import com.bms.entity.Post;
import com.bms.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	ImageRepository imageRepository;
	
	public List<Image> findByPostId(Post post){
		return imageRepository.findByPost(post);
	}
	
}
