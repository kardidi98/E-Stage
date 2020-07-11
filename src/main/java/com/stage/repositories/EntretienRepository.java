package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {

}
