package com.stage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.stage.entities.DemandeStage;

@Controller
public class DemandeStageController {

	@GetMapping("request")
	public String request(Model model) {
		model.addAttribute("request", new DemandeStage());
		return "DemandeStage";
	}
	
	@PostMapping("saveRequest")
	public String saveRequest(Model model) {
		return "redirect:/request?added";
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
