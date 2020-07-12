package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.DemandeStage;
@Repository
public interface DemandeStageRepository extends JpaRepository<DemandeStage, Long>{

}
