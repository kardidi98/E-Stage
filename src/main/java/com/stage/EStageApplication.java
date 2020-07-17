package com.stage;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.stage.entities.ResponsableStages;
import com.stage.entities.Role;
import com.stage.repositories.ResponsableStagesRepository;


@SpringBootApplication
public class EStageApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EStageApplication.class, args);
		
	}

	
	
	@Bean
	public CommandLineRunner demo(ResponsableStagesRepository rsr) {
		return args->{
				//rsr.save(new ResponsableStages("Choukri", "Anwar", "resp", "responsable@gmail.com", passwordEncoder.encode("123456"), null, Arrays.asList(new Role("Role_ResponsableStage"),new Role("Role_Utilisateur")), "BH123456"));
			};
	}

}
