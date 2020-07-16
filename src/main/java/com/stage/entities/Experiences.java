package com.stage.entities;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private String entreprise;
	private YearMonth dateDeb;
	private YearMonth dateFin;
	@Column(columnDefinition = "LONGTEXT")
	private String Description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "demandeStage")
	private DemandeStage demandeStage;

	public Experiences(String titre, String institution, YearMonth dateDeb, YearMonth dateFin, String description,
			DemandeStage demandeStage) {
		
		this.titre = titre;
		this.entreprise = institution;
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

	

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public YearMonth getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(YearMonth dateDeb) {
		this.dateDeb = dateDeb;
	}

	public YearMonth getDateFin() {
		return dateFin;
	}

	public void setDateFin(YearMonth dateFin) {
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
