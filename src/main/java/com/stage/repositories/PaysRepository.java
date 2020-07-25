package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Pays;

public interface PaysRepository extends JpaRepository<Pays, String> {

}
