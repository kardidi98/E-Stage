package com.stage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stage.entities.Utilisateur;
import com.stage.service.DemandeStageService;

@Controller
public class HomeController {

	@Autowired
	private DemandeStageService requestService;


	@GetMapping("/home")
	public String index(Model model,HttpServletRequest hsr) {
		HttpSession session = hsr.getSession(true);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user=requestService.findbyUsername(auth.getName().toString());
		if(session.getAttribute("user")==null) {
			session.setAttribute("user",user);
		}
		if(session.getAttribute("user")!=null) {

			model.addAttribute("notifications", user.getNotifivations());
		}

		return "index";
	}

	@GetMapping("")
	public String home(Model model,HttpServletRequest hsr) {

		return "redirect:home";
	}


	@GetMapping("about")
	public String about(Model model,HttpServletRequest hsr) {
		HttpSession session = hsr.getSession(true);
		if(session.getAttribute("user")!=null) {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Utilisateur user=requestService.findbyUsername(auth.getName().toString());
			session.setAttribute("user",user);
			model.addAttribute("notifications", user.getNotifivations());
		}
		return "about_us";
	}

	@GetMapping("contact")
	public String contact(Model model,HttpServletRequest hsr) {
		HttpSession session = hsr.getSession(true);
		if(session.getAttribute("user")!=null) {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Utilisateur user=requestService.findbyUsername(auth.getName().toString());
			session.setAttribute("user",user);
			model.addAttribute("notifications", user.getNotifivations());
		}
		return "contact_us";
	}


}
