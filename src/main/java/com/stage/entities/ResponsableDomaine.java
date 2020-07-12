package com.stage.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_ResponsableDomaine")
public class ResponsableDomaine extends Utilisateur {
	
	
	private String CIN;
	private Domaine domaine;

	
	@OneToMany(mappedBy = "responsableDomaine",cascade = CascadeType.ALL)
	private List<DemandeStage> demadeStages;
	
	@OneToMany(mappedBy = "responsableDomaine",cascade = CascadeType.ALL)
	private List<Entretien> entretiens;

	public ResponsableDomaine(String nom, String prenom, String login, String email, String password,Collection<Role> roles,String cIN, Domaine domaine, List<DemandeStage> demadeStages,
			List<Entretien> entretiens) {
		super(nom, prenom, login, email, password,roles);
		CIN = cIN;
		this.domaine = domaine;
		this.demadeStages = demadeStages;
		this.entretiens = entretiens;
	}

	public ResponsableDomaine(String nom, String prenom, String login, String email, String password,Collection<Role> roles,String cIN, Domaine domaine) {
		super(nom, prenom, login, email, password,roles);
		CIN = cIN;
		this.domaine = domaine;
		
	}
	
	public ResponsableDomaine() {
		
	}



	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public List<DemandeStage> getDemadeStages() {
		return demadeStages;
	}

	public void setDemadeStages(List<DemandeStage> demadeStages) {
		this.demadeStages = demadeStages;
	}

	public List<Entretien> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(List<Entretien> entretiens) {
		this.entretiens = entretiens;
	}
	
	
}
