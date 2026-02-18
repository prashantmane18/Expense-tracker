package com.expence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests(auth -> 
			auth.anyRequest().permitAll()
				)
		
		.formLogin(login -> 
			login.loginPage("/login")
			.defaultSuccessUrl("/dashboard")
				)
		.logout(logout -> 
			logout.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
				);
		
		return http.build();
	}
}
