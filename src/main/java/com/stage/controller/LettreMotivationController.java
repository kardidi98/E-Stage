package com.stage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.stage.repositories.LettreMotivationRepository;

@Controller
public class LettreMotivationController {

	@Autowired
	private LettreMotivationRepository lettreRepository;
	
	
	
}
