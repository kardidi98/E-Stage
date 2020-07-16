package com.stage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stage.entities.DemandeStage;
import com.stage.entities.DocumentAdministratif;
import com.stage.entities.Domaine;
import com.stage.entities.Experiences;
import com.stage.entities.Formations;
import com.stage.entities.ResponsableDomaine;
import com.stage.entities.Stagiaire;
import com.stage.repositories.DemandeStageRepository;
import com.stage.repositories.ResponsableDomaineRepository;
import com.stage.repositories.StagiaireRepository;

@Service
public class DemandeStageService {


	@Autowired
	private DemandeStageRepository requestRepository;
	
	@Autowired
	private StagiaireRepository stagiaireRepository;
	
	@Autowired
	private ResponsableDomaineRepository responsableDomaineRepository;

	public Stagiaire findbyUsername(String email) {
		
		return stagiaireRepository.findByEmail(email);
	}
	
	public ResponsableDomaine findResponsableDomaine(String email) {
		
		return responsableDomaineRepository.findByEmail(email);
	}

	public void setMultipartFiles(DemandeStage request, MultipartFile photo, List<MultipartFile> titreDoc,
			MultipartFile titre) {
		
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
	
	
}
