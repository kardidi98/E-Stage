package com.stage.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Entretien {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	
	
	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;

	public Entretien(long id, LocalDate date, ResponsableDomaine responsableDomaine) {
		this.id = id;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	public ResponsableDomaine getResponsableDomaine() {
		return responsableDomaine;
	}

	public void setResponsableDomaine(ResponsableDomaine responsableDomaine) {
		this.responsableDomaine = responsableDomaine;
	}
	

}
