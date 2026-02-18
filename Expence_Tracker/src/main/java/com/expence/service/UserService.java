package com.expence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expence.Repository.UserRepository;
import com.expence.model.User;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username).orElseThrow();
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
		
	}
	
	public User saveUser(User user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	public User getUserByUsername(String username)
	{
		return userRepo.findByUsername(username).orElseThrow();
	}
}
