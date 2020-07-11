package com.stage.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Entretien {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date date;
	
	@OneToOne
	private DemandeStage demandeStage;
	
	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;

	public Entretien(long id, Date date, DemandeStage demandeStage, ResponsableDomaine responsableDomaine) {
		this.id = id;
		this.date = date;
		this.demandeStage = demandeStage;
		this.responsableDomaine = responsableDomaine;
	}

	public Entretien() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}

	public ResponsableDomaine getResponsableDomaine() {
		return responsableDomaine;
	}

	public void setResponsableDomaine(ResponsableDomaine responsableDomaine) {
		this.responsableDomaine = responsableDomaine;
	}
	

}
