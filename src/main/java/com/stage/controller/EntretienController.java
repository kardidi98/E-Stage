package com.stage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntretienController {

	@GetMapping("interview")
	public String interview(Model model) {
		return "entretien";
	}
	
	
}