package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
