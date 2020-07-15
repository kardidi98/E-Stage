package com.stage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stage.entities.DemandeStage;
import com.stage.entities.DocumentAdministratif;
import com.stage.entities.Experiences;
import com.stage.entities.Formations;
import com.stage.entities.Stagiaire;
import com.stage.service.DemandeStageService;

@Controller
public class DemandeStageController {
	
	
	@Autowired
	private DemandeStageService requestService;
	
	
	@ModelAttribute("request")
	public DemandeStage DemandeStage() {
		
		return new DemandeStage();
	}
	
	

	@GetMapping("request")
	public String request(Model model) {
		
		DemandeStage demandeStage=new DemandeStage();
		demandeStage.getFormations().add(new Formations());
		demandeStage.getExperiences().add(new Experiences());
		demandeStage.getDocumentAdministratif().add(new DocumentAdministratif());
		
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Stagiaire user =  requestService.findbyUsername(auth.getName().toString());
		
		model.addAttribute("request", demandeStage);
		model.addAttribute("stagiaire", user);
		return "DemandeStage";
	}
	
	@PostMapping(value="saveRequest")
	public String saveRequest(Model model,@ModelAttribute("request") DemandeStage request,
			@RequestParam(name="photo") MultipartFile photo,
			@RequestParam(name="titreDoc") List<MultipartFile> titreDoc,
			@RequestParam(name="titre") MultipartFile titre) { 
		
		requestService.setMultipartFiles(request,photo,titreDoc,titre);
		
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Stagiaire user =  requestService.findbyUsername(auth.getName().toString());
		request.setStagiaire(user);
		requestService.save(request);
		
		request.setFormations();
		request.setDocumentsAdministratifs();
		request.setExperiences();
		requestService.save(request);
		
		return "redirect:/request?requestAdded";
	}
	
	
	@GetMapping("changeStatus")
	public String changeStatus(Model model) {
		return "requestDtails";
	}
	
	
	@GetMapping("makeDecision")
	public String makeDecision(Model model) {
		return "requestDtails";
	}
	
	
}
