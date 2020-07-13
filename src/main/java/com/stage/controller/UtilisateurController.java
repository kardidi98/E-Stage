package com.stage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stage.entities.Utilisateur;
import com.stage.repositories.UtilisateurRepository;
import com.stage.service.UserService;
import com.stage.web.dto.UserRegistrationDto;

@Controller
public class UtilisateurController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	@ModelAttribute("stagiaire")
	public UserRegistrationDto UserRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("login")
	public String login() {
		
		
		return "login";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		return "Register";
	}
	
	@PostMapping("register")
	public String CreateAccount(Model model,@ModelAttribute("stagiaire") UserRegistrationDto userRegistrationDto,@RequestParam("Rpassword") String confirmPassword) {
		Utilisateur user= utilisateurRepository.findByEmail(userRegistrationDto.getEmail());
		if(user!=null) {
			
			return "redirect:/register?existingEmail";
		}
		else if(!userRegistrationDto.getPassword().equals(confirmPassword)) {
			return "redirect:/register?passwordError";
		}
		userService.save(userRegistrationDto);
		return "redirect:/login?success";
	}
	
	@GetMapping("403")
	public String accessDenied(Model model) {
		
		return "403";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		
		return "redirect:/login?logout";
	}
}
