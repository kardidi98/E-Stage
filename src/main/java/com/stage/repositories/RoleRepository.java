package com.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
