package com.stage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stage.entities.DemandeStage;
import com.stage.entities.Domaine;
import com.stage.entities.Stagiaire;
import com.stage.entities.Utilisateur;
import com.stage.repositories.StagiaireRepository;

@Controller
public class StagiaireController {
	
	@Autowired
	private StagiaireRepository stagiaireRepository;

	@GetMapping("userSentRequests")
	public String requests(Model model,@RequestParam(value = "domain") Domaine domain,HttpServletRequest hsr) {
		HttpSession session = hsr.getSession(true);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Stagiaire stagiaire=stagiaireRepository.findByEmail(auth.getName().toString());
		session.setAttribute("user",stagiaire);
		
		List<DemandeStage> demandesStages = stagiaire.getDemandeStagesByDomaine(domain);
		model.addAttribute("demandesStages",demandesStages);

		model.addAttribute("notifications", stagiaire.getNotifivations());
		model.addAttribute("domaine",domain);
		return "listRequests";
	}
	
}
