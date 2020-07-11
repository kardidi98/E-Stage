package com.stage.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String contenu;
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "responsableStages")
	private ResponsableStages responsableStages;

	public Commentaire(long id, String contenu, ResponsableStages responsableStages) {
		this.id = id;
		this.contenu = contenu;
		this.date = LocalDate.now();
		this.responsableStages = responsableStages;
	}

	public Commentaire() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ResponsableStages getResponsableStages() {
		return responsableStages;
	}

	public void setResponsableStages(ResponsableStages responsableStages) {
		this.responsableStages = responsableStages;
	}
	
	
	
	
	
}
