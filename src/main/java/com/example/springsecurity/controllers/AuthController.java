package com.example.springsecurity.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/v1/")
@CrossOrigin(value = "*")
public class AuthController {

	
	@PostMapping("createUser")
	public String createuser() {
		return "this";
	}
}
