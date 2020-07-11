package com.stage.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Experiences {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	private String institution;
	private Date dateDeb;
	private Date dateFin;
	@Column(columnDefinition = "LONGTEXT")
	private String Description;
	
	@ManyToOne
	@JoinColumn(name = "demandeStage")
	private DemandeStage demandeStage;

	public Experiences(long id, String titre, String institution, Date dateDeb, Date dateFin, String description,
			DemandeStage demandeStage) {
		this.id = id;
		this.titre = titre;
		this.institution = institution;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		Description = description;
		this.demandeStage = demandeStage;
	}

	public Experiences() {
		
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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}
	
	
	
}
