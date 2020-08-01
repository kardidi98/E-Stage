package com.stage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stage.entities.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "select * from notification where demande_stage = :id",nativeQuery = true)
	List<Notification> findByRequestId(@Param("id") long id);

}
