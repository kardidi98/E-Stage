package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Experiences;
@Repository
public interface ExperienceRepository extends JpaRepository<Experiences, Long> {

}
