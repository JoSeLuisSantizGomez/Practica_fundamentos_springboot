package com.example.demo.caseUse;

import java.util.List;

import com.example.demo.entity.UserPass;
import com.example.demo.service.UserServices;

public class GetUserPassImplement implements GetUserPass {
	private UserServices userServices;
	
	public GetUserPassImplement(UserServices userServices) {
		this.userServices = userServices;
	}



	@Override
	public List<UserPass> getAll() {
		return userServices.getAllUsers();
	}

}
