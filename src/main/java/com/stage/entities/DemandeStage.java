package com.stage.entities;

import java.util.Date;
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

@Entity
public class DemandeStage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Statut statut;
	private Domaine domaine;
	private Date dateDeb;
	private Date dateFin;
	
	@OneToMany(mappedBy = "demandeStage",cascade = CascadeType.ALL)
	private List<Notification> notifications;
	
	@OneToOne
	private LettreMotivation lettreMotivation;
	
	@OneToOne
	private EtatCivile etatCivile;
	
	@OneToOne
	private Hobbies hobbies;
	
	@ManyToOne
	@JoinColumn(name = "stagiaire")
	private Stagiaire stagiaire;
	
	@OneToMany(mappedBy = "demandeStage")
	private List<Formations> formations;
	
	@OneToMany(mappedBy = "demandeStage")
	private List<Experiences> experiences;
	
	@OneToMany(mappedBy = "demandeStage")
	private List<DocumentAdministratif> documentAdministratif;
	
	@OneToOne
	private Entretien entretien;
	
	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;

	public DemandeStage(long id, Statut statut, Domaine domaine, Date dateDeb, Date dateFin,
			List<Notification> notifications, LettreMotivation lettreMotivation, EtatCivile etatCivile, Hobbies hobbies,
			Stagiaire stagiaire, List<Formations> formations, List<Experiences> experiences,
			List<DocumentAdministratif> documentAdministratif, Entretien entretien,
			ResponsableDomaine responsableDomaine) {
		this.id = id;
		this.statut = statut;
		this.domaine = domaine;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.notifications = notifications;
		this.lettreMotivation = lettreMotivation;
		this.etatCivile = etatCivile;
		this.hobbies = hobbies;
		this.stagiaire = stagiaire;
		this.formations = formations;
		this.experiences = experiences;
		this.documentAdministratif = documentAdministratif;
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
	
	
	
}
