package com.example.springsecurity.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home/v1/")
@CrossOrigin(value = "*")
public class HomeController {

	
	@GetMapping("home")
	public String Welcome() {
		return "this";
	}
}
