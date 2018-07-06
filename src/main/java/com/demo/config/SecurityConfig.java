package com.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	CustomAuthFailureHandler customAuthFailureHandler;
	
	@Autowired
	CustomAuthSuccessHandler customAuthSuccessHandler;

	@Autowired
	LoginAuthEntryPoint loginAuthEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            .cors().and()
			.authorizeRequests()
				.antMatchers("/**").authenticated()
				.antMatchers("/login").permitAll()
				.antMatchers("/").permitAll()
			.and()
				.formLogin()
					.failureHandler(customAuthFailureHandler)
					.successHandler(customAuthSuccessHandler)
            .and()
                .httpBasic()
			.and()
				.exceptionHandling().authenticationEntryPoint(loginAuthEntryPoint)
			.and()
				.csrf().disable();

	}
	
	@Override
	public void configure(WebSecurity web) {
		web
			.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
					.usersByUsernameQuery("select username,password, 1 as enabled from users where username = ?")
					.authoritiesByUsernameQuery("select username, rol as authority from users where username = ?")
					.dataSource(dataSource)
					.passwordEncoder(new BCryptPasswordEncoder());

	}
}
