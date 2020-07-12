package com.stage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.stage.entities.Stagiaire;
import com.stage.entities.Utilisateur;
import com.stage.repositories.StagiaireRepository;
import com.stage.repositories.UtilisateurRepository;

@SpringBootApplication
public class EStageApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EStageApplication.class, args);
		
	}

	
	@Bean
	public CommandLineRunner demo(StagiaireRepository sr, UtilisateurRepository ur) {
		return args->{
			
			};
	}

}
