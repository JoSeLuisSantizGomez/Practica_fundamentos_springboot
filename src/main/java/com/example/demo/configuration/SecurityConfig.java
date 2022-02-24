package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserServices;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserServices userServices;
	
	public SecurityConfig(UserServices userServices) {
		this.userServices = userServices;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
		throws Exception{
			auth.userDetailsService(userServices).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
    		.formLogin()
    		.loginPage("/login")
    		.defaultSuccessUrl("/")
    		.failureUrl("/login?error=true")
    		.permitAll()
    			.and()
			.authorizeRequests()
			//.antMatchers(HttpMethod.GET,"/api/users/*").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
				.and()
			.csrf().disable()
				.logout()
				.logoutSuccessUrl("/login?logout=true")
				.invalidateHttpSession(true)
				.permitAll();
	}

}
