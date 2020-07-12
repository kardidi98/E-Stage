package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.ResponsableStages;
@Repository
public interface ResponsableStagesRepository extends JpaRepository<ResponsableStages, Long> {

}
