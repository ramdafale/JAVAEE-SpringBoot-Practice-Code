package com.spring.security.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.security.web.entity.Role;
import com.spring.security.web.entity.User;
import com.spring.security.web.repository.UserRepository;

@SpringBootApplication
public class TrainingSpringSecurityDemoApplication implements CommandLineRunner{

	@Autowired
	UserRepository repository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TrainingSpringSecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		Role role = new Role("ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		roles.add(new Role("USER"));
		User user = new User("Sanjay", "koala", roles) ;
		repository.saveAndFlush(user);
		
		Set<Role> darsRole = new HashSet<>();
		darsRole.add(new Role("USER"));
		User user2 = new User("Darshit", "koala", darsRole) ;
		repository.saveAndFlush(user2);
		
		
		
		
		
		/*
	
		Role role = new Role("ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		roles.add(new Role("USER"));
		User user = new User("Ram", "password", roles) ;
		repository.saveAndFlush(user);
		
		Set<Role> darsRole = new HashSet<>();
		darsRole.add(new Role("USER"));
		User user2 = new User("Rahul", "pass", darsRole) ;
		repository.saveAndFlush(user2);	
		
		Set<Authority> adminAuthority = new HashSet<>();
		adminAuthority.add(new Authority("UPDATE"));
		adminAuthority.add(new Authority("DELETE"));
		adminAuthority.add(new Authority("VIEW"));*/
		
	}
	
	
	
}
