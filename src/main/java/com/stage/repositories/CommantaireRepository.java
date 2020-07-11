package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Commentaire;

public interface CommantaireRepository extends JpaRepository<Commentaire, Long> {

}
