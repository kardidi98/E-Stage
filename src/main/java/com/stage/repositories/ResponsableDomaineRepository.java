package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Domaine;
import com.stage.entities.ResponsableDomaine;
import com.stage.entities.Utilisateur;
@Repository
public interface ResponsableDomaineRepository extends JpaRepository<ResponsableDomaine, Long> {

	public ResponsableDomaine findByEmail(String email);

	public ResponsableDomaine findByDomaine(Domaine domaine);

}
