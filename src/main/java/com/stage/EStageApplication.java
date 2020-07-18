package com.stage;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.stage.entities.Domaine;
import com.stage.entities.ResponsableDomaine;
import com.stage.entities.ResponsableStages;
import com.stage.entities.Role;
import com.stage.entities.Utilisateur;
import com.stage.repositories.ResponsableStagesRepository;
import com.stage.repositories.RoleRepository;
import com.stage.repositories.UtilisateurRepository;


@SpringBootApplication
public class EStageApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EStageApplication.class, args);
		
	}

	
	
	@Bean
	public CommandLineRunner demo(UtilisateurRepository ur) {
		return args->{
				
//				ResponsableStages rs=new ResponsableStages("Choukri", "Anwar", "Choukri75", "ChoukriAnwar@gmail.com",
//						"$2a$10$f9A/WLem0UWup20Izl7Boe/vxWWsuAYJ0q4iPBfk8Ndb3lf4hmgCC" , null,
//						Arrays.asList(new Role("Role_ResponsableStage"),new Role("Role_Utilisateur")), "BH123456");
//				
//				ur.save(rs);
//				
//				
//				ResponsableDomaine rdinfo=new ResponsableDomaine("Skalli", "Noura", "Skalli90", "SkalliNoura@gmail.com",
//						"$2a$10$He19RZoGHCRTstNVK.azX.2QmgOWVeDc81ouBQLI6GD4tW02p9JMi" ,
//						null, Arrays.asList(new Role("Role_ResponsableDomaine"),new Role("Role_Utilisateur")),
//						"JH333456",Domaine.Computer_Science);
//				
//				ur.save(rdinfo);
//			
//			ResponsableDomaine rdCommerce=new ResponsableDomaine("Marnissi", "Alaa", "Alaa91", "MarnissiAlaa@gmail.com",
//					"$2a$10$1H4ydPzJq9fHxAWs5F4g7eJwi2mXDA5avYA8yt5CzgJGQKgFtcVBu" ,
//					null, Arrays.asList(new Role("Role_ResponsableDomaine"),new Role("Role_Utilisateur")),
//					"AB337882",Domaine.Commerce);
//			
//			ur.save(rdCommerce);
//			
//			ResponsableDomaine rdIndus=new ResponsableDomaine("Ahmed", "Lamiae", "LamiaeAhmed91", "AhmedLamiae@gmail.com",
//			"$2a$10$.zX0tIjBeAGsmYMAx7YgheyAjcsaQ.IZQlosh7b5PKr5wAlT.dMI6" ,
//			null, Arrays.asList(new Role("Role_ResponsableDomaine"),new Role("Role_Utilisateur")),
//			"JL45636",Domaine.Industry);
//	
//			ur.save(rdIndus);
//			
//			ResponsableDomaine rdFinance=new ResponsableDomaine("Maknassi", "Hamid", "Hamid91", "MaknassiHamid@gmail.com",
//			"$2a$10$haAf80L4pI8LCz0vSinMYe11TwBXeFpgkxvzcys8oN.ct/Yu8juyK" ,
//			null, Arrays.asList(new Role("Role_ResponsableDomaine"),new Role("Role_Utilisateur")),
//			"BG156369",Domaine.Finance);
//	
//			ur.save(rdFinance);
			};
	}

}
