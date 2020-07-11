package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {

}
