package com.stage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("about")
	public String about(Model model) {
		return "about_us";
	}
	
	@GetMapping("contact")
	public String contact(Model model) {
		return "contact_us";
	}
	
	
}
