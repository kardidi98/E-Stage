package com.stage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.stage.entities.Utilisateur;
import com.stage.service.DemandeStageService;

@Controller
public class DemandeStageController {


	@Autowired
	private DemandeStageService requestService;

	@Value("${dir.dirPhotoIdentity}")
	private String dirPhotoIdentity;

	@Value("${dir.dirLettreMotivation}")
	private String dirLettreMotivation;

	@Value("${dir.dirDocumentAdministratif}")
	private String dirDocumentAdministratif;


	@RequestMapping(value="getPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long request) throws FileNotFoundException, IOException {
		File f=new File(dirPhotoIdentity+request);
		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@RequestMapping(value="CoverLetter",produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] CoverLetter(Long request) throws FileNotFoundException, IOException {
		File f=new File(dirLettreMotivation+request);
		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@RequestMapping(value="AdministratifDocument",produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] AdministratifDocument(Long request,Long document) throws FileNotFoundException, IOException {

		File f=new File(dirDocumentAdministratif+request+"_"+document);
		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@ModelAttribute("request")
	public DemandeStage DemandeStage() {

		return new DemandeStage();
	}


	@GetMapping("request")
	public String request(Model model,HttpServletRequest hsr) {


		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user=requestService.findbyUsername(auth.getName().toString());

		model.addAttribute("notifications", user.getNotifivations());

		DemandeStage demandeStage=new DemandeStage();
		demandeStage.getFormations().add(new Formations());
		demandeStage.getExperiences().add(new Experiences());
		demandeStage.getDocumentAdministratif().add(new DocumentAdministratif());



		model.addAttribute("request", demandeStage);
		model.addAttribute("stagiaire", user);



		return "DemandeStage";
	}

	@PostMapping(value="saveRequest")
	public String saveRequest(Model model,@ModelAttribute("request") DemandeStage request,
			@RequestParam(name="photo") MultipartFile photo,
			@RequestParam(name="titreDoc") List<MultipartFile> titreDoc,
			@RequestParam(name="titre") MultipartFile titre) throws IllegalStateException, IOException { 

		requestService.setMultipartFiles(request,photo,titreDoc,titre);


		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user =  requestService.findbyUsername(auth.getName().toString());
		request.setStagiaire((Stagiaire) user);
		requestService.save(request);

		request.setFormations();
		request.setExperiences();
		if(!titreDoc.get(0).getOriginalFilename().isEmpty()) {
			request.setDocumentsAdministratifs();
		}
		requestService.save(request);

		requestService.createAndSaveFile(photo,dirPhotoIdentity,titre,dirLettreMotivation,titreDoc,dirDocumentAdministratif,request);
		requestService.addNotification(request,"NouvelleDemandeAjoutee");

		return "redirect:/Edit?id="+request.getId()+"&requestAdded";
	}



	@GetMapping("requests")
	public String requests(Model model,@RequestParam(value = "domain") Domaine domain,HttpServletRequest hsr) {

		HttpSession session = hsr.getSession(true);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user=requestService.findbyUsername(auth.getName().toString());
		session.setAttribute("user",user);

		List<DemandeStage> demandesStages = requestService.findByDomaine(domain);
		model.addAttribute("demandesStages",demandesStages);

		model.addAttribute("notifications", user.getNotifivations());
		model.addAttribute("domaine",domain);
		return "listRequests";
	}


	@GetMapping("Edit")
	public String Edit(Model model,@RequestParam(value = "id") Long id,HttpServletRequest hsr ) {

		HttpSession session = hsr.getSession(true);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user=requestService.findbyUsername(auth.getName().toString());
		session.setAttribute("user",user);
		model.addAttribute("notifications", user.getNotifivations());

		DemandeStage demandStage = requestService.findById(id);
		model.addAttribute("demandStage", demandStage);

		return "requestDetails";
	}

	@GetMapping("Delete")
	public String Delete(Model model,@RequestParam(value = "id") Long id ) throws IOException {
		DemandeStage demandStage=requestService.findById(id);
		requestService.deleteRequest(demandStage,dirDocumentAdministratif,dirLettreMotivation,dirPhotoIdentity);


		return "redirect:requests?domain="+demandStage.getDomaine()+"&requestDeleted";
	}

	@GetMapping("changeStatus")
	public String changeStatus(Model model,@RequestParam(value = "id") Long id,@RequestParam("status") Statut status ) {

		DemandeStage demandStage = requestService.findById(id);
		demandStage.setStatut(status);
		requestService.save(demandStage);

		return "redirect:Edit?id="+id+"&statusChanged";
	}


	@GetMapping("makeDecision")
	public String makeDecision(Model model,HttpServletRequest hsr, @RequestParam(value = "id") Long id,@RequestParam(name="decision",defaultValue = "") String decision) {

		DemandeStage demandStage = requestService.findById(id);
		if(decision.equals("Refused")) {
			Statut status=Statut.valueOf(decision);

			demandStage.setStatut(status);
			requestService.save(demandStage);
			return "redirect:Edit?id="+id+"&statusChanged";
		}

		HttpSession session = hsr.getSession(true);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user=requestService.findbyUsername(auth.getName().toString());
		session.setAttribute("user",user);

		model.addAttribute("notifications", user.getNotifivations());
		model.addAttribute("demandStage", demandStage);
		model.addAttribute("entretien", new Entretien());
		return "entretien";
	}



	@PostMapping("interview")
	public String interview(Model model,@RequestParam(value = "id") Long id,@ModelAttribute("entretien") Entretien entretien) {

		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur responsable =  requestService.findbyUsername(auth.getName().toString());
		entretien.setResponsableDomaine((ResponsableDomaine) responsable);

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
