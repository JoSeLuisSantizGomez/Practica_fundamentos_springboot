package com.example.demo.caseUse;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

public class GetUserImplement implements GetUser {
	private UserService userService;

	public GetUserImplement(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<User> getAll() {
		return userService.getAllUsers();
	}

}
