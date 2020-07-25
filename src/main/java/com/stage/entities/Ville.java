package com.stage.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ville {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nom;

	private String pays;
	
	private String province;
	
	@OneToMany(mappedBy = "ville",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<EtatCivile> etatsCiviles;
	
	
	
	public Ville(String nom, String pays, String province,List<EtatCivile> etatsCiviles) {
		this.nom = nom;
		this.pays = pays;
		this.province = province;
		this.etatsCiviles=etatsCiviles;
	}
	
	
	public List<EtatCivile> getEtatsCiviles() {
		return etatsCiviles;
	}


	public void setEtatsCiviles(List<EtatCivile> etatsCiviles) {
		this.etatsCiviles = etatsCiviles;
	}


	public Ville() {
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	

}
