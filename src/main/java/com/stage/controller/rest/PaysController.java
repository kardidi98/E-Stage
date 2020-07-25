package com.stage.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.entities.Pays;
import com.stage.repositories.PaysRepository;

@RestController
public class PaysController {

	
	@Autowired
	PaysRepository paysRepository;
	
	@GetMapping("Pays")
	public List<Pays> listVilles(){
		return paysRepository.findAll();
	}
	
	
}
