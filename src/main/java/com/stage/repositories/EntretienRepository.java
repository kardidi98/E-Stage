package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Entretien;
@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {

}
