package com.optimal.solutions.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {
	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/manageronly")
	public String manageronly() {
		return "manageronly";
	}
}
