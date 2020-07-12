package com.stage.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DocumentAdministratif {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	
	@ManyToOne
	@JoinColumn(name = "demandeStage")
	private DemandeStage demandeStage;

	public DocumentAdministratif(long id, String titre, DemandeStage demandeStage) {
		this.id = id;
		this.titre = titre;
		this.demandeStage = demandeStage;
	}

	public DocumentAdministratif() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}
	
	
	
}