package com.stage.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_Stagiaire")
public class Stagiaire extends Utilisateur {

	@OneToMany(mappedBy = "stagiaire")
	private List<DemandeStage> demandeStages;

	public Stagiaire(String nom, String prenom, String login, String email, String password,List<DemandeStage> demandeStages) {
		super(nom, prenom, login, email, password);
		this.demandeStages = demandeStages;
	}
	

	public Stagiaire(String nom, String prenom, String login, String email, String password) {
		super(nom, prenom, login, email, password);
		
	}


	public Stagiaire() {
		
	}
	
	
	
}
