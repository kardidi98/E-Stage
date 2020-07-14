package com.stage.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.list.LazyList;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DemandeStage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Statut statut;
	private Domaine domaine;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeb;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFin;
	

	@OneToOne(cascade = CascadeType.ALL)
	private EtatCivile etatCivile;
	
	@OneToMany(mappedBy = "demandeStage",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Formations> formations= new ArrayList<Formations>();
	
	@OneToMany(mappedBy = "demandeStage",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Experiences> experiences=new ArrayList<Experiences>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private LettreMotivation lettreMotivation;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Hobbies hobbies;
	
	@OneToMany(mappedBy = "demandeStage",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<DocumentAdministratif> documentAdministratif=new ArrayList<DocumentAdministratif>();
	
	@OneToMany(mappedBy = "demandeStage",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Notification> notifications= new ArrayList<Notification>();
	
	
	@ManyToOne
	@JoinColumn(name = "stagiaire")
	private Stagiaire stagiaire;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Entretien entretien;
	
	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;
	


	public DemandeStage(Statut statut, Domaine domaine, LocalDate dateDeb, LocalDate dateFin, EtatCivile etatCivile,
			List<Formations> formations, List<Experiences> experiences, LettreMotivation lettreMotivation,
			Hobbies hobbies, List<DocumentAdministratif> documentAdministratif, List<Notification> notifications,
			Stagiaire stagiaire, Entretien entretien, ResponsableDomaine responsableDomaine) {
		this.statut = statut;
		this.domaine = domaine;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.etatCivile = etatCivile;
		this.formations=formations;
		this.experiences = experiences;
		this.lettreMotivation = lettreMotivation;
		this.hobbies = hobbies;
		this.documentAdministratif = documentAdministratif;
		this.notifications = notifications;
		this.stagiaire = stagiaire;
		this.entretien = entretien;
		this.responsableDomaine = responsableDomaine;
	}

	public DemandeStage() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
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

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public LettreMotivation getLettreMotivation() {
		return lettreMotivation;
	}

	public void setLettreMotivation(LettreMotivation lettreMotivation) {
		this.lettreMotivation = lettreMotivation;
	}

	public EtatCivile getEtatCivile() {
		return etatCivile;
	}

	public void setEtatCivile(EtatCivile etatCivile) {
		this.etatCivile = etatCivile;
	}

	public Hobbies getHobbies() {
		return hobbies;
	}

	public void setHobbies(Hobbies hobbies) {
		this.hobbies = hobbies;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public List<Formations> getFormations() {
		return formations;
	}

	public void setFormations(List<Formations> formations) {
		
		this.formations = formations;
	}
	
	

	public List<Experiences> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experiences> experiences) {
		this.experiences = experiences;
	}

	public List<DocumentAdministratif> getDocumentAdministratif() {
		return documentAdministratif;
	}

	public void setDocumentAdministratif(List<DocumentAdministratif> documentAdministratif) {
		this.documentAdministratif = documentAdministratif;
	}

	public Entretien getEntretien() {
		return entretien;
	}

	public void setEntretien(Entretien entretien) {
		this.entretien = entretien;
	}

	public ResponsableDomaine getResponsableDomaine() {
		return responsableDomaine;
	}

	public void setResponsableDomaine(ResponsableDomaine responsableDomaine) {
		this.responsableDomaine = responsableDomaine;
	}
	
	public void setFormations() {
		for (Formations f : this.formations) {
			f.setDemandeStage(this);
		}
	}
	
	public void setNotifications() {
		for (Notification n : this.notifications) {
			n.setDemandeStage(this);
		}
	}
	
	public void setExperiences() {
		for (Experiences e : this.experiences) {
			e.setDemandeStage(this);
		}
	}
	
	public void setDocumentsAdministratifs() {
		for (DocumentAdministratif d : this.documentAdministratif) {
			d.setDemandeStage(this);
		}
	}
	
	
	
}
