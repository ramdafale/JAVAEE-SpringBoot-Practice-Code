package com.spring.security.web.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.security.web.entity.User;
import com.spring.security.web.repository.UserRepository;

public class TrainingUserDetailsService implements UserDetailsService {

	private final UserRepository repository;
	
	public TrainingUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<User> optionalUser =  repository.findByUserName(username);
	  optionalUser
	  .orElseThrow(() -> new  UsernameNotFoundException("Username is not available"));
	  return optionalUser.map(user -> 
	  new org.springframework.security.core.userdetails.User(user.getUserName(), 
			  user.getPassword(), getAuthorities(user))).get();
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream().map(role-> 
			new SimpleGrantedAuthority("ROLE_" + role.getRole()))
		.collect(Collectors.toList());
	}

}
