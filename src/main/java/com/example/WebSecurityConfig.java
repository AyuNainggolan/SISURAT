package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/lib/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/listSurat")
		.loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("admin").roles("ADMIN");
//		auth.inMemoryAuthentication()
//		.withUser("user").password("user").roles("USER");
//	}
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, 1 from user_account where username=?")
		.authoritiesByUsernameQuery("select username, role from user_account where username=?");
	}
	
}
