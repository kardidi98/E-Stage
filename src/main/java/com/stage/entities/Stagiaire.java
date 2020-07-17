package com.stage.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_Stagiaire")
public class Stagiaire extends Utilisateur {

	@OneToMany(mappedBy = "stagiaire",cascade = CascadeType.ALL)
	private List<DemandeStage> demandeStages;

	public Stagiaire(String nom, String prenom, String login, String email, String password,List<Notification> notifivations,Collection<Role> roles,List<DemandeStage> demandeStages) {
		super(nom, prenom, login, email, password,notifivations, roles);
		this.demandeStages = demandeStages;
	}
	

	public Stagiaire(String nom, String prenom, String login, String email, String password,List<Notification> notifivations,Collection<Role> roles) {
		super(nom, prenom, login, email, password,notifivations, roles);
		
	}


	public Stagiaire() {
		
	}


	public List<DemandeStage> getDemandeStages() {
		return demandeStages;
	}


	public void setDemandeStages(List<DemandeStage> demandeStages) {
		this.demandeStages = demandeStages;
	}
	
	
	
}
