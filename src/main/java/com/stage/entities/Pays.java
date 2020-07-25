package com.stage.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pays {
	
	private String nom;
	@Id
	private String code;
	private String Capitale;

	
	@OneToMany(mappedBy = "pays",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<EtatCivile> etatsCiviles;
	
	public Pays(String nom, String code, String capitale,List<EtatCivile> etatsCiviles) {
		this.nom = nom;
		this.code = code;
		Capitale = capitale;
		
		this.etatsCiviles=etatsCiviles;
	}
	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public List<EtatCivile> getEtatsCiviles() {
		return etatsCiviles;
	}
	public void setEtatsCiviles(List<EtatCivile> etatsCiviles) {
		this.etatsCiviles = etatsCiviles;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCapitale() {
		return Capitale;
	}
	public void setCapitale(String capitale) {
		Capitale = capitale;
	}

	
	
	
	

}
