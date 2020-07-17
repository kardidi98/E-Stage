package com.stage.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stage.entities.DemandeStage;
import com.stage.entities.DocumentAdministratif;
import com.stage.entities.Domaine;
import com.stage.entities.EtatCivile;
import com.stage.entities.Experiences;
import com.stage.entities.Formations;
import com.stage.entities.LettreMotivation;
import com.stage.entities.Notification;
import com.stage.entities.ResponsableDomaine;
import com.stage.entities.ResponsableStages;
import com.stage.entities.Stagiaire;
import com.stage.entities.Utilisateur;
import com.stage.repositories.DemandeStageRepository;
import com.stage.repositories.ResponsableDomaineRepository;
import com.stage.repositories.ResponsableStagesRepository;
import com.stage.repositories.StagiaireRepository;
import com.stage.repositories.UtilisateurRepository;

@Service
public class DemandeStageService {


	@Autowired
	private DemandeStageRepository requestRepository;


	@Autowired
	private UtilisateurRepository userRepository;





	public Utilisateur findbyUsername(String email) {

		return userRepository.findByEmail(email);
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

	public void deleteRequest(DemandeStage request, String dirDocumentAdministratif, String dirLettreMotivation, String dirPhotoIdentity) throws IOException {
		DemandeStage demandeStage=request;

		requestRepository.delete(request);
		deteleFiles(demandeStage,dirDocumentAdministratif,dirLettreMotivation,dirPhotoIdentity);
	}

	public void addNotification(DemandeStage request,String messageSeparateurDuTraitement) {
		if(messageSeparateurDuTraitement==("NouvelleDemandeAjoutee")) {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Utilisateur responsable=userRepository.findByEmail(auth.getName().toString());
			Notification notification=new Notification("A new request has been received", request,responsable);
			notification.setDemandeStage(request);

			request.getNotifications().add(notification);
			request.setNotifications(request.getNotifications());
			requestRepository.save(request);
		}

	}










}
