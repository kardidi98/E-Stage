package com.stage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.stage.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();

		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("ChoukriAnwar@gmail.com").password("{bcrypt}ChoukriAnwarResponsableStage").roles("Role_ResponsableStage", "Role_Utilisateur");
		auth.inMemoryAuthentication().withUser("SkalliNoura@gmail.com").password("{bcrypt}SkalliNouraResponsableDomaineInfo").roles("Role_ResponsableDomaine", "Role_Utilisateur");
		auth.inMemoryAuthentication().withUser("MarnissiAlaa@gmail.com").password("{bcrypt}MarnissiAlaaResponsableDomaineCommerce").roles("Role_ResponsableDomaine", "Role_Utilisateur");
		auth.inMemoryAuthentication().withUser("AhmedLamiae@gmail.com").password("{bcrypt}AhmedLamiaeResponsableDomaineIndus").roles("Role_ResponsableDomaine", "Role_Utilisateur");
		auth.inMemoryAuthentication().withUser("MaknassiHamid@gmail.com").password("{bcrypt}MaknassiHamidResponsableDomaineFinance").roles("Role_ResponsableDomaine", "Role_Utilisateur");
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(
				"/home",
				"/",
				"/login",
				"/register",
				"/listRequests",
				"/about",
				"/contact",
				"/assets/js/**",
				"/assets/css/**",
				"/assets/img/**",
				"/assets/vendor/**"
				).permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/request")
		.hasAuthority("Role_Stagiaire")
		.and()
		.authorizeRequests()
		.antMatchers("/Delete")
		.hasAnyAuthority("Role_Stagiaire","Role_ResponsableStage")
		.and()
		.authorizeRequests()
		.antMatchers("/requests")
		.hasAnyAuthority("Role_ResponsableStage","Role_ResponsableDomaine")
		.and()
		.authorizeRequests()
		.antMatchers("/userSentRequests")
		.hasAuthority("Role_Stagiaire")
		.and()
		.authorizeRequests()
		.antMatchers("/interview")
		.hasAuthority("Role_ResponsableDomaine")
		.and()
		.authorizeRequests()
		.antMatchers("/Edit")
		.hasAuthority("Role_Utilisateur")
		.and()
		.authorizeRequests()
		.antMatchers("/Villes/**","/Pays/**")
		.hasAuthority("Role_Utilisateur")
		.and()
		.authorizeRequests()
		.antMatchers("/makeDecision")
		.hasAuthority("Role_ResponsableDomaine")
		.and()
		.authorizeRequests()
		.antMatchers("/changeStatus")
		.hasAuthority("Role_ResponsableStage")
		.and()
		.authorizeRequests()
		.antMatchers("/Assign")
		.hasAuthority("Role_ResponsableStage")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403")

		;





	}

}
