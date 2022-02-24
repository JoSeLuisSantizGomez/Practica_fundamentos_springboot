package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.example.demo.caseUse.GetUser;
import com.example.demo.caseUse.GetUserImplement;
import com.example.demo.caseUse.GetUserPass;
import com.example.demo.caseUse.GetUserPassImplement;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServices;

@Configuration
public class CaseUseConfiguration {
	
	@Bean
	GetUser getUser (UserService userService) {
		return new GetUserImplement(userService);
	}
	
	@Bean
	GetUserPass getUserPass(UserServices userServices) {
		return new GetUserPassImplement(userServices);
	}

}
