package com.stage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stage.entities.Utilisateur;
import com.stage.service.DemandeStageService;
import com.stage.service.MailNotificationService;
import com.sun.mail.handlers.message_rfc822;

@Controller
public class HomeController {

	@Autowired
	private DemandeStageService requestService;

	@Autowired
	private MailNotificationService mailService;

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

	@GetMapping("/")
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

	@PostMapping("contact")
	public String sendContact(Model model,HttpServletRequest hsr,@RequestParam("name") String nom,
			@RequestParam("subject") String subject,@RequestParam("email")String email,@RequestParam("message") String msg) {

		String contactUsMessage="New message from loyal customer:<br/>&nbsp;<table border='1' style='border:1px solid black; width:100%;'>" + 
				"  <tr style='background-color:rgba(0, 103, 213,0.5)'>" + 
				"    <th colspan='2'><strong>Message Content</strong></th>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td><strong>Name</strong></td>" + 
				"    <td>"+nom+"</td>" + 
				"  </tr>" + 

				"  <tr>" + 
				"    <td><strong>Email</strong></td>" + 
				"    <td>"+email+"</td>" + 
				"  </tr>" + 
				
				"  <tr>" + 
				"    <td><strong>Subject</strong></td>" + 
				"    <td>"+subject+"</td>" + 
				"  </tr>" + 
				
				"  <tr>" + 
				"    <td><strong>Message</strong></td>" + 
				"    <td>"+msg+"</td>" + 
				"  </tr>" + 
				"</table>";

		try {
			mailService.sendContactUsMessage(contactUsMessage);
		} catch (Exception e) {

		}
		return "redirect:contact?sendSuccessfully";
	}


}
