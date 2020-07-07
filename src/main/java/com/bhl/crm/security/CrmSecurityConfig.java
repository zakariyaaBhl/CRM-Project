package com.bhl.crm.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class CrmSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	// add a reference to our security data source
	@Autowired
	//@Qualifier("securityDataSource")
	private DataSource securityDataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("zakariyaa").password(bcpe.encode("1234")).roles("ADMIN").and()
		 * .withUser("youssef").password(bcpe.encode("1234")).roles("EMPLOYEE").and()
		 * .passwordEncoder(bcpe);
		 */
		
		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(bcpe);
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * http.authorizeRequests().anyRequest().authenticated().and()
		 * .formLogin().loginPage("/loginPage").loginProcessingUrl(
		 * "/authenticatedTheUser").permitAll().and() .logout().permitAll();
		 */
		
		http.authorizeRequests()
		.antMatchers("/addForm","/updateForm","/delete","/add","/update").hasRole("ADMIN")
		.antMatchers("/").hasAnyRole("ADMIN","MANAGER","EMPLOYEE")
		.and()
		.formLogin()
		.loginPage("/loginPage")
		.loginProcessingUrl("/authenticatedTheUser")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
