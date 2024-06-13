package com.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.Notification;
import com.bms.entity.User;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>{
	List<Notification> findByUserOrderByIdDesc(User user);
	
	@Modifying
    @Transactional
	@Query(value = "update notification set seen = 1 where seen = 0 and user_id = :userId", nativeQuery = true)
	void updateSeen(@Param(value = "userId") long userId);
	
	long countByUserAndSeenFalse(User user);
}
