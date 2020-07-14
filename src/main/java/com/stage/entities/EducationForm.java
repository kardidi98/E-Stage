package com.stage.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;


public class EducationForm {
	
	@SuppressWarnings("unchecked")
	private List<Formations> formation=LazyList.decorate(new ArrayList<Formations>(),FactoryUtils.instantiateFactory(Formations.class));

	
	
	public EducationForm() {
		
	}

	public EducationForm(List<Formations> formations) {
		this.formation = formations;
	}

	public List<Formations> getFormations() {
		return formation;
	}

	public void setFormations(List<Formations> formations) {
		this.formation = formations;
	}
	
	

}
