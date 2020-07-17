package com.stage.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class LettreMotivation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	@Column(columnDefinition = "LONGTEXT")
	private String contenu;
	


	public LettreMotivation(String titre, String contenu) {
		
		this.titre = titre;
		this.contenu = contenu;
	}

	public LettreMotivation() {
		
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

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public void createFile(MultipartFile titre, String path, DemandeStage request) throws IllegalStateException, IOException {
		Path dir = Paths.get(path);
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				//fail to create directory
				e.printStackTrace();
			}
		}
		if(!titre.isEmpty()) {
			if(!Files.exists(Paths.get(path+request.getId()))) {
				titre.transferTo(new File(path+request.getId()));
			}
			
		}
		
	}

	public void deleteFile(String path, long id) throws IOException {
		if(Files.exists(Paths.get(path+id))) {
			Files.delete(Paths.get(path+id));
		}
		
	}


	
	
}
