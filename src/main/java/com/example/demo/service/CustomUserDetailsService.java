package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	

	private UserRepo userRepo;
	public CustomUserDetailsService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepo.findByUsername(username);
	}


}
