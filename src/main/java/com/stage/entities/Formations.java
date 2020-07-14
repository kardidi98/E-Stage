package com.stage.entities;

import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Formations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	private String institution;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeb;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFin;
	@Column(columnDefinition = "LONGTEXT")
	private String Description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "demandeStage_id")
	private DemandeStage demandeStage;
	


	public Formations(Long id,String titre, String institution, LocalDate dateDeb, LocalDate dateFin, String description,
			DemandeStage demandeStage) {
		this.id=id;
		this.titre = titre;
		this.institution = institution;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		Description = description;
		this.demandeStage = demandeStage;
	}

	public Formations() {
		
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

	public LocalDate getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		this.dateDeb = dateDeb;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
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
