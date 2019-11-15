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
			.mvcMatchers("/", "/infomation").permitAll()
			.anyRequest().hasAnyRole(ProjectConst.MEMBER_ROLE, ProjectConst.ADMIN_ROLE)
			.and()
		.formLogin()
			.and()
		.httpBasic();
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joonAdmin").password("{noop}123").roles(ProjectConst.ADMIN_ROLE).and()
			.withUser("joonUser").password("{noop}123").roles(ProjectConst.MEMBER_ROLE).and()
			;
	}
}
 