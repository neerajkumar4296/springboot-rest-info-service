package com.weatherinfoservice.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.weatherinfoservice.security.ApplicationUserRoles;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Value("${rest-info-service.users}")
	private List<String> users;
	
	@Value("${rest-info-service.passwords}")
	private List<String> passwords;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		         authBuilder.inMemoryAuthentication()
		         .withUser(users.get(0)).password(passwordEncoder().encode(passwords.get(0))).roles(ApplicationUserRoles.USER.name())
		         .and()
		         .withUser(users.get(1)).password(passwordEncoder().encode(passwords.get(1))).roles(ApplicationUserRoles.ADMIN.name());
		         
		         //UserDetails userDetails= User.builder().username("default").password("default").roles("user").build();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http                 .csrf().disable()
							 .authorizeRequests()
							 .antMatchers(HttpMethod.GET, "/booking/**", "/weather/**")
							 .hasAnyRole(ApplicationUserRoles.USER.name(), ApplicationUserRoles.ADMIN.name())
							 .antMatchers(HttpMethod.POST).hasRole(ApplicationUserRoles.ADMIN.name())
							 .and()
							 .httpBasic();
	}
	
	 @Override
	 public void configure(WebSecurity web) throws Exception {
	        web
	            .ignoring()
	            .antMatchers("/h2-console/**","/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**","/actuator/health");
	    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
