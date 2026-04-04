package com.cg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cg.service.MyUserDetailService;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityAppApplication {
	@Autowired
	MyUserDetailService myUserDetailService;

	public static void main(String[] args) {
		
		SpringApplication.run(SpringSecurityAppApplication.class, args);
		System.out.println("Spring Security Application is running...");
	}
	
	@Bean
	public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())   // for REST API
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.authenticationProvider(getAuthenticationProvider())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(myUserDetailService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

// csrf ----> Cross Site Request Forgery // it is a type of attack where the attacker tricks the user into performing actions on a web application without their consent.
// daoAuthentication Provider ----> userDetailService -----> Repository ----> Database
