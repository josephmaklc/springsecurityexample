package com.optimal.solutions.springsecuritydemo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// To use this configuration, uncomment the 2 annotations below, and comment it out for the other WebSecurityConfig files
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfigBasicAuth {

	/**
	 * http basic auth dialog, the user is defined in application.properties, 
	 * spring.security.user.name=
	 * spring.security.user.password=
	 * spring.security.user.roles=
	 * 
	 * However, that is just one user can be specified that way, and it really is... basic authentication
	 * 
	 * or you can implement userDetailsService as a Bean with some hardcoded users, or make it come from a database
	 * 
	 * However, Basic Auth does not have concept of logout, use incongnito mode to try again
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
		return http.build();

	}

/*
 	// userDetailsService can also be your own routine that returns a UserDetailsService with a bunch of UserDetails
 	// if you don't specify, it will be using the user defined in application.properties
	@Bean
	public UserDetailsService userDetailsService() {

		// User Role
		UserDetails theUser = User.withUsername("john")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("john123")
				.roles("USER").build();

		// User Role
		UserDetails theUser2 = User.withUsername("peter")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("peter123")
				.roles("USER").build();

		// Manager Role
		UserDetails theManager = User.withUsername("mary")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("mary123")
				.roles("MANAGER").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theUser2);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;
	}
*/
}