package com.stage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.stage.entities.Pays;
import com.stage.entities.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long> {

	@Query("SELECT v FROM Ville v WHERE v.pays = :x")
	List<Ville> findByPays(@Param("x") String code);

}
