package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll().anyRequest().authenticated()
				.antMatchers("/welcome").authenticated()
				.antMatchers("/addNewPost").authenticated()
				.antMatchers("/documents").authenticated()
				.antMatchers("/accountInfo").authenticated()
				.antMatchers("/searchPost").authenticated()
				.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll()
			.and().httpBasic()
			.and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		
		for(User user : userRepository.findAll()) {
			auth
			.inMemoryAuthentication()
				.withUser(user.getUsername()).password(user.getPassword()).roles("USER");
		}

	}
}
