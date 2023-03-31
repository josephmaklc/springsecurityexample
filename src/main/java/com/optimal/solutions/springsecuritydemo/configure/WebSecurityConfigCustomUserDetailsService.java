package com.optimal.solutions.springsecuritydemo.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//To use this configuration, uncomment the 2 annotations below, and comment it out for the other WebSecurityConfig files
// also uncomment the @Service at MyUserDetailsService
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfigCustomUserDetailsService {
	
	@Autowired
	public MyUserDetailsService customUserDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.requestMatchers("/manageronly/**").hasRole("MANAGER")
				.anyRequest().authenticated()
			)
 			.userDetailsService(customUserDetailsService) // <-- instead of useDetailsService() method, specify custom User Detail service here
			.logout((logout) -> logout.permitAll()).formLogin(); // <-- if want to use custom login form, see the other class
 		return http.build();
	}

	
}