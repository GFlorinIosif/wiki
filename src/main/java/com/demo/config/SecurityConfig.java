package com.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.handlers.CustomAuthFailureHandler;
import com.demo.handlers.CustomAuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	CustomAuthFailureHandler customAuthFailureHandler;
	
	@Autowired
	CustomAuthSuccessHandler customAuthSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").authenticated()
				.antMatchers("/addNewPost").authenticated()
				.antMatchers("/documents").authenticated()
				.antMatchers("/accountInfo").authenticated()
				.antMatchers("/searchPost").authenticated()
				.antMatchers("/getPostList").permitAll()
				.antMatchers("/filtreazaLista").permitAll()
				.antMatchers("/").permitAll().anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.failureHandler(customAuthFailureHandler)
					.successHandler(customAuthSuccessHandler)
			.and()
				.logout().permitAll()
			.and()
				.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**");
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		
//		for(User user : userRepository.findAll()) {
//			auth
//			.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//				.withUser(user.getUsername()).password(user.getPassword()).roles(user.getRol());
//		}
		
		auth
			.jdbcAuthentication()
					.usersByUsernameQuery("select username,password, 1 as enabled from users where username = ?")
					.authoritiesByUsernameQuery("select username, rol as authority from users where username = ?")
					.dataSource(dataSource)
					.passwordEncoder(new BCryptPasswordEncoder());

	}
}
