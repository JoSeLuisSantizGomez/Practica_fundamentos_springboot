package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserPass;
import com.example.demo.repository.UserPassRepository;
@Service
public class UserServices implements UserDetailsService {

	private UserPassRepository userPassRepository;
	
	public UserServices(UserPassRepository userPassRepository) {
		this.userPassRepository = userPassRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPass userPass = userPassRepository.findByNombre(username);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("admin"));
		
		UserDetails userDetails = new User( userPass.getNombre(), userPass.getClave(), roles );
		
		return userDetails;
	}
	
	public List<UserPass> getAllUsers(){
		return (List<UserPass>) userPassRepository.findAll() ;
	}

}
