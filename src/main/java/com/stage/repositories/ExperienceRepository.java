package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Experiences;

public interface ExperienceRepository extends JpaRepository<Experiences, Long> {

}
