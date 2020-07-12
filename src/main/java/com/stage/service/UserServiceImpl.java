package com.stage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stage.entities.Role;
import com.stage.entities.Stagiaire;
import com.stage.entities.Utilisateur;
import com.stage.repositories.StagiaireRepository;
import com.stage.repositories.UtilisateurRepository;
import com.stage.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UtilisateurRepository userRepository;
	
	@Autowired
	private StagiaireRepository stagiaireRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Utilisateur save(UserRegistrationDto registrationDto) {
		Stagiaire stagiaire=new Stagiaire(registrationDto.getNom(), registrationDto.getPrenom(),
				registrationDto.getLogin(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("Role_Stagiaire")));
		return stagiaireRepository.save(stagiaire);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user= userRepository.findByEmail(username);
		System.out.println(username);
		
		if(user.equals(null)) {
			throw new UsernameNotFoundException("Invalid email or password !");
		}
		return new User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNom())).collect(Collectors.toList());
	}

}
