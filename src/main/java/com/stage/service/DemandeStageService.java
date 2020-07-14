package com.stage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.entities.Stagiaire;
import com.stage.repositories.StagiaireRepository;

@Service
public class DemandeStageService {

	@Autowired
	private StagiaireRepository stagiaireRepository;

	public Stagiaire findbyUsername(String email) {
		
		return stagiaireRepository.findByEmail(email);
	}
	
	
}
