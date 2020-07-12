package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.DocumentAdministratif;
@Repository
public interface DocumentAdministratifRepository extends JpaRepository<DocumentAdministratif, Long> {

}
