package com.stage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stage.entities.DemandeStage;
import com.stage.entities.Domaine;
@Repository
public interface DemandeStageRepository extends JpaRepository<DemandeStage, Long>{

	
	List<DemandeStage> findByDomaine(Domaine domain);

}
