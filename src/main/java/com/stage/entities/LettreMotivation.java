package com.stage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LettreMotivation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	@Column(columnDefinition = "LONGTEXT")
	private String contenu;
	
	@OneToOne
	private DemandeStage demandeStage;

	public LettreMotivation(long id, String titre, String contenu, DemandeStage demandeStage) {
		this.id = id;
		this.titre = titre;
		this.contenu = contenu;
		this.demandeStage = demandeStage;
	}

	public LettreMotivation() {
		
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

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}
	
	
}
