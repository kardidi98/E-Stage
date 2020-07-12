package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Commentaire;

@Repository
public interface CommantaireRepository extends JpaRepository<Commentaire, Long> {

}
