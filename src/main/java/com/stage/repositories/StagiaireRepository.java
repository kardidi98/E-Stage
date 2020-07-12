package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Stagiaire;
import com.stage.entities.Utilisateur;
@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {

	public Stagiaire findByEmail(String email);

	
}
