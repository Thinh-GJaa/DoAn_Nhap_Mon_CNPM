package com.bms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Accomodation;
import com.bms.entity.Post;
import com.bms.repository.AccomodationRepository;

@Service
public class AccomodationService {
	@Autowired
	AccomodationRepository accomodationRepository;

	public Accomodation findByPostid(Post post) {
		return accomodationRepository.findByPost(post);
	}

	public boolean insertAccomodation(Accomodation accomodation) {
		return accomodationRepository.save(accomodation) != null;
	}
}
