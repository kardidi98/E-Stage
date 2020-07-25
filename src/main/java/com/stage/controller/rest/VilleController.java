package com.stage.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stage.entities.Ville;
import com.stage.repositories.LettreMotivationRepository;
import com.stage.repositories.VilleRepository;

@RestController
@CrossOrigin(origins = "http://localhost:9090/request")
public class VilleController {

	@Autowired
	VilleRepository villeRepository;
	
	@RequestMapping(value="Villes/{code}",method = RequestMethod.GET)
	public List<Ville> listVilles(@PathVariable("code") String code){
		return villeRepository.findByPays(code);
	}
	
	@RequestMapping(value="Villes/",method = RequestMethod.GET)
	public List<Ville> listVilles(){
		return villeRepository.findAll();
	}
	
	
}
