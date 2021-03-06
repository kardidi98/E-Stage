package com.stage.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.list.LazyList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class DemandeStage implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	@Enumerated(EnumType.STRING)
	private DecisionFinale finalDecision;
	@Enumerated(EnumType.STRING)
	private Domaine domaine;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeb;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFin;


	private boolean DecisionTomake;


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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Commentaire commentaire;


	@ManyToOne
	@JoinColumn(name = "stagiaire")
	private Stagiaire stagiaire;

	@OneToOne(cascade = CascadeType.ALL)
	private Entretien entretien;

	@ManyToOne
	@JoinColumn(name = "responsableDomaine")
	private ResponsableDomaine responsableDomaine;






	public DemandeStage(Statut statut, DecisionFinale finalDecision, Domaine domaine, LocalDate dateDeb,
			LocalDate dateFin, boolean decisionTomake, EtatCivile etatCivile, List<Formations> formations,
			List<Experiences> experiences, LettreMotivation lettreMotivation, Hobbies hobbies,
			List<DocumentAdministratif> documentAdministratif, List<Notification> notifications,
			Commentaire commentaire, Stagiaire stagiaire, Entretien entretien, ResponsableDomaine responsableDomaine) {
		this.statut = statut;
		this.finalDecision = finalDecision;
		this.domaine = domaine;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		DecisionTomake = decisionTomake;
		this.etatCivile = etatCivile;
		this.formations = formations;
		this.experiences = experiences;
		this.lettreMotivation = lettreMotivation;
		this.hobbies = hobbies;
		this.documentAdministratif = documentAdministratif;
		this.notifications = notifications;
		this.commentaire = commentaire;
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



	public boolean isDecisionTomake() {
		return DecisionTomake;
	}

	public void setDecisionTomake(boolean decisionTomake) {
		DecisionTomake = decisionTomake;
	}

	public DecisionFinale getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(DecisionFinale finalDecision) {
		this.finalDecision = finalDecision;
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
		this.formations.clear();
		this.formations.addAll(formations);
	}



	public List<Experiences> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experiences> experiences) {
		this.experiences.clear();
		this.experiences.addAll(experiences);
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

	
	
	public Commentaire getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Commentaire commentaires) {
		this.commentaire = commentaires;
	}

	public Object clone() throws
	CloneNotSupportedException 
	{ 

		return super.clone(); 
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

	public void removeFormation(Formations formation) {
		this.formations.remove(formation);
	}

	public void removeExperience(Experiences experience) {
		this.experiences.remove(experience);
	}

	public void setOrUpdate(Commentaire comment) {
		if(this.getCommentaire()==null) {
			this.setCommentaire(comment);
			
		}
		else {
			this.getCommentaire().setContenu(comment.getContenu());
		}
		
	}

	public void updatePhoto(String dirPhotoIdentity, MultipartFile photo) throws IllegalStateException, IOException {
		File f=new File(dirPhotoIdentity+this.getId());
		if(f.exists()) {
			byte[] bytes=photo.getBytes();
			Path path=Paths.get(dirPhotoIdentity+this.getId());
			Files.write(path, bytes);		
		}
		else 
		{

			photo.transferTo(new File(dirPhotoIdentity+this.getId()));
		}
		
	}

	public void updateLetter(String dirLettreMotivation, MultipartFile titre) throws IOException {
		File f=new File(dirLettreMotivation+this.getId());
		if(f.exists()) {
			byte[] bytes=titre.getBytes();
			Path path=Paths.get(dirLettreMotivation+this.getId());
			Files.write(path, bytes);		
		}
		else 
		{

			titre.transferTo(new File(dirLettreMotivation+this.getId()));
		}
		
	}

	public void updateDocs(String dirDocumentAdministratif, List<MultipartFile> titreDoc) throws IOException {
		for (MultipartFile doc : titreDoc) {
			File f=new File(dirDocumentAdministratif+this.getId()+"_"+titreDoc.indexOf(doc));
			if(f.exists()) {
				byte[] bytes=doc.getBytes();
				Path path=Paths.get(dirDocumentAdministratif+this.getId()+"_"+titreDoc.indexOf(doc));
				Files.write(path, bytes);		
			}
			else 
			{

				doc.transferTo(new File(dirDocumentAdministratif+this.getId()+"_"+titreDoc.indexOf(doc)));
			}
		}
		
	}





//	public void deteleFiles(String dirDocumentAdministratif, String dirLettreMotivation,String dirPhotoIdentity) throws IOException {
//		this.getEtatCivile().deleteFile(dirPhotoIdentity,this.getId());
//		this.getLettreMotivation().deleteFile(dirLettreMotivation,this.getId());
//		for (DocumentAdministratif doc : this.getDocumentAdministratif()) {
//			doc.deleteFile(dirDocumentAdministratif,this.getId(),this.getDocumentAdministratif().indexOf(doc));
//		}
//
//	}


}
