package com.optimal.solutions.springsecuritydemo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//To use this configuration, uncomment the 2 annotations below, and comment it out for the other WebSecurityConfig files
// the user for this form is defined in application.properties
// or you can implement userDetailsService as a Bean with some hardcoded users, or make it come from a database

@Configuration
@EnableWebSecurity
public class WebSecurityConfigLoginForm {

	boolean useCustomForm = false;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// note the .formLogin(), if nothing in parameter then a default form is provided
		// if using a custom login form (for example /login) , specify like the following
		if (useCustomForm) {

			http.authorizeHttpRequests(
					(requests) -> requests.requestMatchers("/", "/home").permitAll()
					.requestMatchers("/manageronly/**").hasRole("MANAGER")      
					.anyRequest().authenticated())
					.formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());

		} else {
			http.authorizeHttpRequests(
					(requests) -> requests.requestMatchers("/", "/home").permitAll()
					.requestMatchers("/manageronly/**").hasRole("MANAGER")
					.anyRequest().authenticated())
					.logout((logout) -> logout.permitAll()).formLogin();

		}

		return http.build();
	}

	
 	// userDetailsService can also be your own routine that returns a UserDetailsService with a bunch of UserDetails
 	// if you don't specify, it will be using the user defined in application.properties
	@Bean
	public UserDetailsService userDetailsService() {

		// User Role
		UserDetails theUser = User.withUsername("user")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("user$")
				.roles("USER").build();

		// User Role
		UserDetails theUser2 = User.withUsername("user2")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("user2$")
				.roles("USER").build();

		// Manager Role
		UserDetails theManager = User.withUsername("manager")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("manager$")
				.roles("MANAGER").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theUser2);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;
	}

}