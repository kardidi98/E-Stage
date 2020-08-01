package com.stage.entities;

import java.time.LocalDate;
import java.util.ArrayList;
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


	public List<DemandeStage> getDemandeStagesByDomaine(Domaine domain) {
		List<DemandeStage> demandesStage=new ArrayList<DemandeStage>();
		for (DemandeStage demandeStage : this.demandeStages) {
			if(demandeStage.getDomaine().equals(domain)) {
				demandesStage.add(demandeStage);
			}
		}
		return demandesStage;
	}
	
	public List<DemandeStage> getDemandeStagesByDomaineStatusAndDates(Domaine domain, LocalDate dateStart, LocalDate dateFin, Statut status) {
		
		List<DemandeStage> demandesStage=new ArrayList<DemandeStage>();
		for (DemandeStage demandeStage : getDemandeStagesByDomaine(domain)) {
			
			if((demandeStage.getDateDeb().compareTo(LocalDate.parse(dateStart.toString()))<=0 && demandeStage.getDateFin().compareTo(LocalDate.parse(dateFin.toString()))>=0) || demandeStage.getStatut()==status) {
				demandesStage.add(demandeStage);
			}
		}
		return demandesStage;
	}
	
	
}
