package com.stage.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.stage.entities.Utilisateur;
import com.stage.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	public Utilisateur save(UserRegistrationDto registrationDto);
	
	

}
