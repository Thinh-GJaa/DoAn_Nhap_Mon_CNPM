package com.bms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Notification;
import com.bms.entity.User;
import com.bms.repository.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	public List<Notification> listNotiOfuser(User user) {
		return notificationRepository.findByUserOrderByIdDesc(user);
	}

	public void saveNoti(Notification notification) {
		notificationRepository.save(notification);
	}

	public Optional<Notification> findNotiById(Long id) {
		return notificationRepository.findById(id);
	}

	public void setAllSeen(long userId) {
		notificationRepository.updateSeen(userId);
	}
	
	public long countNotifycationNotSeen(User user) {
		return notificationRepository.countByUserAndSeenFalse(user);
	}
}
