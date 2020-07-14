package com.stage.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String contenu;
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "demandeStage")
	private DemandeStage demandeStage;
	
	@OneToOne
	private Utilisateur source;
	
	@OneToOne
	private Utilisateur destination;

	public Notification(long id, String contenu,DemandeStage demandeStage, Utilisateur source,
			Utilisateur destination) {
		this.id = id;
		this.contenu = contenu;
		this.date = LocalDate.now();
		this.demandeStage = demandeStage;
		this.source = source;
		this.destination = destination;
	}

	public Notification() {
		
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

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}

	public Utilisateur getSource() {
		return source;
	}

	public void setSource(Utilisateur source) {
		this.source = source;
	}

	public Utilisateur getDestination() {
		return destination;
	}

	public void setDestination(Utilisateur destination) {
		this.destination = destination;
	}
	
	
}
