package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Formations;
@Repository
public interface FormationRepository extends JpaRepository<Formations, Long> {

}
