package com.stage.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Role_ResponsableStage")
public class ResponsableStages extends Utilisateur {

	private String CIN;
	
	

	public ResponsableStages(String nom, String prenom, String login, String email, String password,List<Notification> notifivations,String cIN,Collection<Role> roles) {
		super(nom, prenom, login, email, password,notifivations, roles);
		CIN = cIN;
		
	}

	
	public ResponsableStages(String nom, String prenom, String login, String email, String password,List<Notification> notifivations, Collection<Role> roles,String cIN) {
		super(nom, prenom, login, email, password, notifivations, roles);
		CIN = cIN;
		
	}


	public ResponsableStages() {
		
	}


	public String getCIN() {
		return CIN;
	}


	public void setCIN(String cIN) {
		CIN = cIN;
	}




	
	
}
