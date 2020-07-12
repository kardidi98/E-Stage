package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.LettreMotivation;
@Repository
public interface LettreMotivationRepository extends JpaRepository<LettreMotivation, Long>{

}
