package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.ResponsableDomaine;
@Repository
public interface ResponsableDomaineRepository extends JpaRepository<ResponsableDomaine, Long> {

	public ResponsableDomaine findByEmail(String email);

}
