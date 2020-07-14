package com.stage.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Hobbies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(columnDefinition = "LONGTEXT")
	private String hobbies;
	


	public Hobbies(String hobbies) {
		
		this.hobbies = hobbies;
	}
	
	


	public Hobbies() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	
	
	
	
}

