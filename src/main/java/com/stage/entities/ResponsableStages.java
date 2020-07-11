package com.stage.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_ResponsableStages")
public class ResponsableStages extends Utilisateur {

	private String CIN;
	
	@OneToMany(mappedBy = "responsableStages")
	private List<Commentaire> commentiares;

	public ResponsableStages(String nom, String prenom, String login, String email, String password,String cIN, List<Commentaire> commentiares) {
		super(nom, prenom, login, email, password);
		CIN = cIN;
		this.commentiares = commentiares;
	}

	
	public ResponsableStages(String nom, String prenom, String login, String email, String password,String cIN) {
		super(nom, prenom, login, email, password);
		CIN = cIN;
		
	}


	public ResponsableStages() {
		
	}
	
	
}
