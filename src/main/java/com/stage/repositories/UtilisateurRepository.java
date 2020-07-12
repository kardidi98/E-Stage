package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stage.entities.Utilisateur;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	@Query("SELECT u FROM Utilisateur u WHERE email LIKE :x")
	public Utilisateur findByEmail(@Param("x") String email);
}
