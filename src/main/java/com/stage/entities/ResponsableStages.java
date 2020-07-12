package com.stage.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_ResponsableStages")
public class ResponsableStages extends Utilisateur {

	private String CIN;
	
	@OneToMany(mappedBy = "responsableStages",cascade = CascadeType.ALL)
	private List<Commentaire> commentiares;

	public ResponsableStages(String nom, String prenom, String login, String email, String password,String cIN, List<Commentaire> commentiares,Collection<Role> roles) {
		super(nom, prenom, login, email, password,roles);
		CIN = cIN;
		this.commentiares = commentiares;
	}

	
	public ResponsableStages(String nom, String prenom, String login, String email, String password,Collection<Role> roles,String cIN) {
		super(nom, prenom, login, email, password,roles);
		CIN = cIN;
		
	}


	public ResponsableStages() {
		
	}
	
	
}
