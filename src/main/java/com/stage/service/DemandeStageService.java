package com.stage.service;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stage.entities.Commentaire;
import com.stage.entities.DecisionFinale;
import com.stage.entities.DemandeStage;
import com.stage.entities.DocumentAdministratif;
import com.stage.entities.Domaine;
import com.stage.entities.Experiences;
import com.stage.entities.Formations;
import com.stage.entities.Notification;
import com.stage.entities.Pays;
import com.stage.entities.ResponsableStages;
import com.stage.entities.Statut;
import com.stage.entities.Utilisateur;
import com.stage.entities.Ville;
import com.stage.repositories.DemandeStageRepository;
import com.stage.repositories.DocumentAdministratifRepository;
import com.stage.repositories.NotificationRepository;
import com.stage.repositories.PaysRepository;
import com.stage.repositories.ResponsableDomaineRepository;
import com.stage.repositories.UtilisateurRepository;
import com.stage.repositories.VilleRepository;

@Service
public class DemandeStageService {


	@Autowired
	private DemandeStageRepository requestRepository;

	@Autowired
	private MailNotificationService mailService;

	@Autowired
	private UtilisateurRepository userRepository;

	@Autowired
	private PaysRepository paysRepository;
	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private ResponsableDomaineRepository responsableDomaineRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private DocumentAdministratifRepository documentAdministartifRepository;


	public Utilisateur findbyUsername(String email) {

		return userRepository.findByEmail(email);
	}

	public Utilisateur findResponsibleByDomaine(Domaine domaine) {

		return responsableDomaineRepository.findByDomaine(domaine);
	}

	public void setMultipartFiles(DemandeStage request, MultipartFile photo, List<MultipartFile> titreDoc,MultipartFile titre) {


		request.getEtatCivile().setPhoto(photo.getOriginalFilename());
		request.getLettreMotivation().setTitre(titre.getOriginalFilename());

		if(!titreDoc.get(0).getOriginalFilename().isEmpty()) {
			for (int i = 0; i<titreDoc.size();i++) {
				DocumentAdministratif doc=new DocumentAdministratif();
				doc.setTitre(titreDoc.get(i).getOriginalFilename());
				request.getDocumentAdministratif().add(doc);
			}
		}



	}



	public void save(DemandeStage request) {


		List<Formations> formations =new CopyOnWriteArrayList<Formations>();
		formations.addAll(request.getFormations());
		List<Experiences> experiences=new CopyOnWriteArrayList<Experiences>();
		experiences.addAll(request.getExperiences());

		for (int i=0; i<formations.size();i++) {
			if(formations.get(i).getDateDeb()==null
					&& formations.get(i).getDateFin()==(null)
					&& formations.get(i).getDescription()==(null)
					&& formations.get(i).getInstitution()==(null) 
					&& formations.get(i).getTitre()==(null)) {
				request.removeFormation(formations.get(i));

			}

		}

		for (int i=0;i<experiences.size();i++) {
			if(experiences.get(i).getDateDeb()==null
					&& experiences.get(i).getDateFin()==(null)
					&& experiences.get(i).getDescription()==(null)
					&& experiences.get(i).getEntreprise()==(null) 
					&& experiences.get(i).getTitre()==(null)) {
				request.removeExperience(experiences.get(i));

			}

		}


		requestRepository.save(request);


	}

	public List<DemandeStage> findByDomaine(Domaine domain) {

		return requestRepository.findByDomaine(domain);
	}

	public List<DemandeStage> FindByFilters(Domaine domain, String dateStart, String dateFin, String entretien,
			Statut status, DecisionFinale decision) {
		List<DemandeStage> demandesStage=new ArrayList<DemandeStage>();

		for (DemandeStage demandeStage : findByDomaine(domain)) {

			if(demandeStage.getEntretien()!=null) {
				if((demandeStage.getDateDeb().compareTo(LocalDate.parse(dateStart.toString()))<=0 && demandeStage.getDateFin().compareTo(LocalDate.parse(dateFin.toString()))>=0) || (demandeStage.getEntretien().getDate().compareTo(LocalDate.parse(entretien.toString()))==0) || (demandeStage.getStatut()==status)  || (demandeStage.getFinalDecision()==decision)) {
					demandesStage.add(demandeStage);
				}
			}
			else if(demandeStage.getFinalDecision()!=null){

				if((demandeStage.getDateDeb().compareTo(LocalDate.parse(dateStart.toString()))<=0 && demandeStage.getDateFin().compareTo(LocalDate.parse(dateFin.toString()))>=0) || (demandeStage.getStatut()==status)  || (demandeStage.getFinalDecision()==decision)) {

					demandesStage.add(demandeStage);
				}

			}
			else {
				if((demandeStage.getDateDeb().compareTo(LocalDate.parse(dateStart.toString()))<=0 && demandeStage.getDateFin().compareTo(LocalDate.parse(dateFin.toString()))>=0) || (demandeStage.getStatut()==status)) {

					demandesStage.add(demandeStage);
				}
			}
		}

		return demandesStage;
	}

	public DemandeStage findById(Long id) {

		return requestRepository.getOne(id);
	}

	public void createAndSaveFile(MultipartFile photo, String dirPhotoIdentity, MultipartFile titre,
			String dirLettreMotivation, List<MultipartFile> titreDoc, String dirDocumentAdministratif,DemandeStage request) throws IllegalStateException, IOException {


		request.getEtatCivile().createFile(photo,dirPhotoIdentity,request);
		request.getLettreMotivation().createFile(titre,dirLettreMotivation,request);
		for (DocumentAdministratif doc : request.getDocumentAdministratif()) {
			doc.createFiles(titreDoc,dirDocumentAdministratif,request);
		}

	}

	private void deteleFiles(DemandeStage request,String dirDocumentAdministratif, String dirLettreMotivation,String dirPhotoIdentity) throws IOException {
		request.getEtatCivile().deleteFile(dirPhotoIdentity,request.getId());
		request.getLettreMotivation().deleteFile(dirLettreMotivation,request.getId());
		for (DocumentAdministratif doc : request.getDocumentAdministratif()) {
			doc.deleteFile(dirDocumentAdministratif,request.getId(),request.getDocumentAdministratif().indexOf(doc));
		}

	}

	public void deleteRequest(DemandeStage request, String dirDocumentAdministratif, String dirLettreMotivation, String dirPhotoIdentity) throws IOException, CloneNotSupportedException {
		DemandeStage demandeStage=(DemandeStage) request.clone();

		//deteleFiles(demandeStage,dirDocumentAdministratif,dirLettreMotivation,dirPhotoIdentity);
		requestRepository.delete(request);

	}

	public void addNotification(DemandeStage request,String messageSeparateurDuTraitement,String email) {

		Utilisateur user=userRepository.findByEmail(email);
		Notification notification=null;
		String msg=null;

		if(messageSeparateurDuTraitement==("NouvelleDemandeAjoutee")) {
			notification=new Notification("A new request has been received", request,user);
			msg="<div align='center' style='color:black;'><h2>A new request has been received</h2><h3><strong>Domain: </strong>"+request.getDomaine()+"</h3><div>--"+LocalDate.now()+"--</div><br/><div><a href='http://localhost:9090/home'><button style='background-color:#51A4FD;border:1px solid #51A4FD;border-radius:5px;box-shadow:0 0 10px rgba(0,0,0,0.3);font-size:25px;'>Click Here</button></a></div><div>";
		}

		if(messageSeparateurDuTraitement==("NouvelleDemandeAffectee")) {
			notification=new Notification("A new request has been assigned to you", request,user);

			msg="<div align='center' style='color:black;'><h2>A new request has been assigned to you</h2><h3><strong>Domain: </strong>"+request.getDomaine()+"</h3><div>--"+LocalDate.now()+"--</div><br/><div><a href='http://localhost:9090/home'><button style='background-color:#51A4FD;border:1px solid #51A4FD;border-radius:5px;box-shadow:0 0 10px rgba(0,0,0,0.3);font-size:25px;'>Click Here</button></a></div><div>";

		}

		if(messageSeparateurDuTraitement==("DecisionPrise")) {
			notification=new Notification("Check The status of your request", request,user);

			msg="<div align='center' style='color:black;'><h2>Check The status of your request</h2><h3><strong>Domain: </strong>"+request.getDomaine()+"</h3><div>--"+LocalDate.now()+"--</div><br/><div><a href='http://localhost:9090/home'><button style='background-color:#51A4FD;border:1px solid #51A4FD;border-radius:5px;box-shadow:0 0 10px rgba(0,0,0,0.3);font-size:25px;'>Click Here</button></a></div><div>";

		}

		if(messageSeparateurDuTraitement==("PrendreDecisionFinale")) {
			notification=new Notification("Make final decision", request,user);

			msg="<div align='center' style='color:black;'><h2>Make final decision</h2><h3><strong>Domain: </strong>"+request.getDomaine()+"</h3><div>--"+LocalDate.now()+"--</div><br/><div><a href='http://localhost:9090/home'><button style='background-color:#51A4FD;border:1px solid #51A4FD;border-radius:5px;box-shadow:0 0 10px rgba(0,0,0,0.3);font-size:25px;'>Click Here</button></a></div><div>";

		}



		notification.setDemandeStage(request);
		request.getNotifications().add(notification);
		request.setNotifications(request.getNotifications());
		requestRepository.save(request);
		try {
			mailService.sendNotification(user, msg);
		} catch (Exception e) {

		}
	}

	public void removeNotification(Long notification,Long request) {

		Notification notif=notificationRepository.getOne(notification);
		DemandeStage demandeStage=requestRepository.getOne(request);
		demandeStage.getNotifications().remove(notif);
		requestRepository.save(demandeStage);

	}

	public void addComment(String commentaire,DemandeStage demandStage) {
		Commentaire comment=new Commentaire(commentaire);
		demandStage.setOrUpdate(comment);

	}

	public List<Pays> selectCountries() {
		return paysRepository.findAll();
	}

	public List<Ville> selectCities(String pays) {
		return villeRepository.findByPays(pays);
	}

	public void  setPaysVille(DemandeStage request, String pays, Long ville) {
		request.getEtatCivile().setPays(paysRepository.getOne(pays));
		request.getEtatCivile().setVille(villeRepository.getOne(ville));
	}

	public void updateMultipartFiles(DemandeStage request, String dirPhotoIdentity, MultipartFile photo, String oldPic,
			String dirDocumentAdministratif, List<MultipartFile> titreDoc, String dirLettreMotivation, MultipartFile titre, String oldletter) throws IllegalStateException, IOException {

		if(photo.isEmpty()) {
			request.getEtatCivile().setPhoto(oldPic);
		}
		else {
			request.getEtatCivile().setPhoto(photo.getOriginalFilename());
			request.updatePhoto(dirPhotoIdentity,photo);
		}


		if(titre.isEmpty()) {
			request.getLettreMotivation().setTitre(oldletter);

		}
		else {
			request.getLettreMotivation().setTitre(titre.getOriginalFilename());
			request.updateLetter(dirLettreMotivation,titre);
		}


		if(titreDoc.get(0).isEmpty()) {
			request.setDocumentAdministratif(documentAdministartifRepository.findByRequestId(request.getId()));
		}
		else {

			for (int i = 0; i<titreDoc.size();i++) {
				DocumentAdministratif doc=new DocumentAdministratif();
				doc.setTitre(titreDoc.get(i).getOriginalFilename());
				request.getDocumentAdministratif().add(doc);
			}
			request.updateDocs(dirDocumentAdministratif,titreDoc);

		}
	}

	public List<Notification> findNotifications(DemandeStage request) {
		return notificationRepository.findByRequestId(request.getId());
		
	}





















}
