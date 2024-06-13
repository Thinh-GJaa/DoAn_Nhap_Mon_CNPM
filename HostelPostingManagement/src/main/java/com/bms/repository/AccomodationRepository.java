package com.bms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.Accomodation;
import com.bms.entity.Post;

@Repository
public interface AccomodationRepository extends CrudRepository<Accomodation, Long> {
	Accomodation findByPost(Post post);
}
