package com.example.demo.caseUse;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Component
public class UpdateUser {
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(User newUser, Long id) {
		return userService.update(newUser, id);
	}
	

}
