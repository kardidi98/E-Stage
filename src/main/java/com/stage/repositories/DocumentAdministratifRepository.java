package com.stage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stage.entities.DocumentAdministratif;
@Repository
public interface DocumentAdministratifRepository extends JpaRepository<DocumentAdministratif, Long> {

	@Query(value = "SELECT * FROM document_administratif d WHERE d.demande_stage in (:x)", nativeQuery = true)
	List<DocumentAdministratif> findByRequestId(@Param("x") long id);

}
