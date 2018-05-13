package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/pengajuan/riwayat/**").hasRole("MAHASISWA")
		.antMatchers("/pengajuan/tambah/**").hasRole("MAHASISWA")
		.antMatchers("/pengajuan/download/**").hasRole("MAHASISWA")
		.antMatchers("/pengajuan/viewall/**").hasRole("STAF")
		.antMatchers("/pengajuan/view/**").hasRole("STAF")
		.antMatchers("/pengajuan/upload/**").hasRole("STAF")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/default")
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
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/lib/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/api/**")
				.antMatchers("/img/**");
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, 1 from user_account where username=?")
		.authoritiesByUsernameQuery("select username, role from user_account where username=?");
	}
	
}
