package com.stage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
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
import com.stage.entities.EducationForm;
import com.stage.entities.Formations;
import com.stage.entities.Stagiaire;
import com.stage.repositories.DemandeStageRepository;
import com.stage.service.DemandeStageService;

@Controller
public class DemandeStageController {
	
	@Autowired
	private DemandeStageRepository requestRepository;
	
	@Autowired
	private DemandeStageService requestService;
	
	@ModelAttribute("request")
	public DemandeStage DemandeStage() {
		return new DemandeStage();
	}
	
	@ModelAttribute("educationForm")
	public EducationForm educationForm() {
		return new EducationForm();
	}
	

	@SuppressWarnings("unchecked")
	@GetMapping("request")
	public String request(Model model) {
		DemandeStage demandeStage=new DemandeStage();
		EducationForm educationForm=new EducationForm();
		
		List<Formations> formations =LazyList.decorate(new ArrayList<Formations>(),FactoryUtils.instantiateFactory(Formations.class));
		educationForm.setFormations(formations);
		model.addAttribute("request", demandeStage);
		model.addAttribute("educationForm", educationForm);
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Stagiaire user =  requestService.findbyUsername(auth.getName().toString());
		model.addAttribute("stagiaire", user);
		return "DemandeStage";
	}
	
	@PostMapping("saveRequest")
	public String saveRequest(Model model,@ModelAttribute("request") DemandeStage request,
			@ModelAttribute("educationForm") EducationForm educationForm,
			@RequestParam(name="photo") MultipartFile photo,
			@RequestParam(name="titreDoc") MultipartFile titreDoc,
			@RequestParam(name="titre") MultipartFile titre) { 
		
		System.out.println("la "+educationForm.getFormations().get(0).getTitre()+" "+educationForm.getFormations().size());
		
		
		DocumentAdministratif doc =new DocumentAdministratif();
		request.getDocumentAdministratif().add(doc);
		request.getEtatCivile().setPhoto(photo.getOriginalFilename());
		request.getLettreMotivation().setTitre(titre.getOriginalFilename());
		request.getDocumentAdministratif().get(0).setTitre(titreDoc.getOriginalFilename());
		
	    request.getFormations().addAll(educationForm.getFormations());
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Stagiaire user =  requestService.findbyUsername(auth.getName().toString());
		request.setStagiaire(user);
		requestRepository.save(request);
		
		request.setFormations();
		request.setDocumentsAdministratifs();
		request.setExperiences();
		requestRepository.save(request);
		
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
