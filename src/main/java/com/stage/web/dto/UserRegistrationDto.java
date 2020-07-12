package com.stage.web.dto;

import java.time.LocalDate;

public class UserRegistrationDto {

	private String nom;
	private String prenom;
	private String login;
	private String email;
	private String password;
	private LocalDate dateInscription;
	
	
	
	
	public UserRegistrationDto(String nom, String prenom, String login, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.email = email;
		this.password = password;
		this.dateInscription=LocalDate.now();
	}
	public UserRegistrationDto() {
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}
	
}
