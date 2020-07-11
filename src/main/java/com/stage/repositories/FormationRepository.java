package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Formations;

public interface FormationRepository extends JpaRepository<Formations, Long> {

}
