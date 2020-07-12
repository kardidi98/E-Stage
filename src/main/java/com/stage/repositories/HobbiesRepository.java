package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.entities.Hobbies;
@Repository
public interface HobbiesRepository extends JpaRepository<Hobbies, Long>{

}
