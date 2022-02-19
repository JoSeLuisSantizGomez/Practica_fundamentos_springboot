package com.example.demo.caseUse;

import org.springframework.stereotype.Component;
import com.example.demo.service.UserService;

@Component
public class DeleteUser {
	private UserService userService;

	public DeleteUser(UserService userService) {
		this.userService = userService;
	}

	public void remove(Long id) {
		userService.delete(id);
	}
	

}
