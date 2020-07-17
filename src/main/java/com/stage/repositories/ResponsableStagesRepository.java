package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.ResponsableStages;
import com.stage.entities.Utilisateur;
@Repository
public interface ResponsableStagesRepository extends JpaRepository<ResponsableStages, Long> {

	public ResponsableStages findByEmail(String string);

}
