package com.stage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stage.entities.DemandeStage;
import com.stage.entities.DocumentAdministratif;
import com.stage.entities.Domaine;
import com.stage.entities.Entretien;
import com.stage.entities.Experiences;
import com.stage.entities.Formations;
import com.stage.entities.ResponsableDomaine;
import com.stage.entities.Stagiaire;
import com.stage.entities.Statut;
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
		if(!titreDoc.get(0).getOriginalFilename().isEmpty()) {
			request.setDocumentsAdministratifs();
		}
		
		
		
		request.setExperiences();
		requestService.save(request);
		
		return "redirect:/Edit?id="+request.getId()+"&requestAdded";
	}
	
	
	
	@GetMapping("requests")
	public String requests(Model model,@RequestParam(value = "domain") Domaine domain) {
		List<DemandeStage> demandesStages = requestService.findByDomaine(domain);
		model.addAttribute("demandesStages",demandesStages);
		
		model.addAttribute("domaine",domain);
		return "listRequests";
	}
	
	
	@GetMapping("Edit")
	public String Edit(Model model,@RequestParam(value = "id") Long id ) {
		
		DemandeStage demandStage = requestService.findById(id);
		model.addAttribute("demandStage", demandStage);
		
		return "requestDetails";
	}
	
	@GetMapping("changeStatus")
	public String changeStatus(Model model,@RequestParam(value = "id") Long id,@RequestParam("status") Statut status ) {
		
		DemandeStage demandStage = requestService.findById(id);
		demandStage.setStatut(status);
		requestService.save(demandStage);
		
		return "redirect:Edit?id="+id+"&statusChanged";
	}
	
	
	@GetMapping("makeDecision")
	public String makeDecision(Model model,@RequestParam(value = "id") Long id,@RequestParam(name="decision",defaultValue = "") String decision) {

		DemandeStage demandStage = requestService.findById(id);
		if(decision.equals("Refused")) {
			Statut status=Statut.valueOf(decision);
			
			demandStage.setStatut(status);
			requestService.save(demandStage);
			return "redirect:Edit?id="+id+"&statusChanged";
		}
		
		model.addAttribute("demandStage", demandStage);
		model.addAttribute("entretien", new Entretien());
		return "entretien";
	}
	
	
	
	@PostMapping("interview")
	public String interview(Model model,@RequestParam(value = "id") Long id,@ModelAttribute("entretien") Entretien entretien) {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		ResponsableDomaine responsable =  requestService.findResponsableDomaine(auth.getName().toString());
		entretien.setResponsableDomaine(responsable);
		
		DemandeStage demandStage = requestService.findById(id);
		if(demandStage.getEntretien()!= null) {
			demandStage.getEntretien().updateEntretien(entretien);
		}
		else{
			demandStage.setEntretien(entretien);
		}
		requestService.save(demandStage);
		
		return "redirect:Edit?id="+id+"&interviewSaved";
		
	}
	
	
	
	
}
