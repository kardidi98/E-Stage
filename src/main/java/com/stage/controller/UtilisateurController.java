package com.stage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.stage.service.UserService;
import com.stage.web.dto.UserRegistrationDto;

@Controller
public class UtilisateurController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("stagiaire")
	public UserRegistrationDto UserRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		return "Register";
	}
	
	@PostMapping("register")
	public String CreateAccount(@ModelAttribute("stagiaire") UserRegistrationDto userRegistrationDto) {
		userService.save(userRegistrationDto);
		return "redirect:/login?success";
	}
}
