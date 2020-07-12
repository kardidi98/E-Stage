package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.EtatCivile;
@Repository
public interface EtatCivileRepositiry extends JpaRepository<EtatCivile, Long> {

}
