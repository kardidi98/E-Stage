package com.stage.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EtatCivile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String numTel;
	private String profile;
	private String photo;
	private String pays;
	private String ville;
	private String adresse;
	private int codePostal;
	
	

	public EtatCivile(String numTel, String profile, String photo, String pays, String ville, String adresse,
			int codePostal) {
		
		this.numTel = numTel;
		this.profile = profile;
		this.photo = photo;
		this.pays = pays;
		this.ville = ville;
		this.adresse = adresse;
		this.codePostal = codePostal;
	}

	public EtatCivile() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	
	
	
	
}
