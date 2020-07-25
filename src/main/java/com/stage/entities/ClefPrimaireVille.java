package com.stage.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ClefPrimaireVille implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nom;
	
	private Pays pays;
	private String province;
	private List<EtatCivile> etatsCiviles;
	
	public ClefPrimaireVille(String nom, Pays pays, String province,List<EtatCivile> etatsCiviles) {
		this.nom = nom;
		this.pays = pays;
		this.province = province;
		this.etatsCiviles=etatsCiviles;
	}
	public ClefPrimaireVille() {
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
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
