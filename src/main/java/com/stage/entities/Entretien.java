package com.stage.entities;

import java.time.LocalDate;
import java.time.LocalTime;
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
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	
	
	
	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;

	

	public Entretien(long id, LocalDate date, LocalTime time, ResponsableDomaine responsableDomaine) {
		this.id = id;
		this.date = date;
		this.time = time;
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

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public void updateEntretien(Entretien entretien) {
		this.setDate(entretien.getDate());
		this.setResponsableDomaine(entretien.getResponsableDomaine());
		this.setTime(entretien.getTime());
	}

}
