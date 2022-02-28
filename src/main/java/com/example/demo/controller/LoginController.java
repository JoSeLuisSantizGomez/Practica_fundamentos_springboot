package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.UserPassRepository;


@Controller
public class LoginController {
	
	private UserPassRepository userPassRepository;
	
	
	public LoginController(UserPassRepository userPassRepository) {
		this.userPassRepository = userPassRepository;
	}

	@GetMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("usuario", userPassRepository.findAll());
		return "inicio";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
