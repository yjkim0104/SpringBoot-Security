package com.me.joon.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.me.joon.spring.common.ProjectConst;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.mvcMatchers("/admin").hasRole(ProjectConst.ADMIN_ROLE)
			.mvcMatchers("/", "/infomation", "/account/**").permitAll()
			.anyRequest().hasAnyRole(ProjectConst.MEMBER_ROLE, ProjectConst.ADMIN_ROLE)
			.and()
		.formLogin()
			.and()
		.httpBasic();
			
	}

	
}
 