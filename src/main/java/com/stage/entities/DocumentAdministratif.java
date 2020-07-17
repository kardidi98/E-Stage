package com.stage.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class DocumentAdministratif {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "demandeStage")
	private DemandeStage demandeStage;

	public DocumentAdministratif(String titre, DemandeStage demandeStage) {
		
		this.titre = titre;
		this.demandeStage = demandeStage;
	}

	public DocumentAdministratif() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public DemandeStage getDemandeStage() {
		return demandeStage;
	}

	public void setDemandeStage(DemandeStage demandeStage) {
		this.demandeStage = demandeStage;
	}
	
	
	public void createFiles(List<MultipartFile> titres, String path,DemandeStage request) throws IllegalStateException, IOException {
		Path dir = Paths.get(path);
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				//fail to create directory
				e.printStackTrace();
			}
		}
		for (MultipartFile file : titres) {
			
			if(!file.isEmpty()) {
				if(!Files.exists(Paths.get(path+request.getId()+"_"+titres.indexOf(file)))) {
					file.transferTo(new File(path+request.getId()+"_"+titres.indexOf(file)));
				}
			}
		}
		
	}

	public void deleteFile(String path, long id,int index) throws IOException {
		if(Files.exists(Paths.get(path+id+"_"+index))) {
			Files.delete(Paths.get(path+id+"_"+index));
		}
		
	}


	
}
