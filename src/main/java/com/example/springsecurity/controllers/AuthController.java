package com.example.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.model.UserModel;
import com.example.springsecurity.requestwrapper.AuthRequestWrapper;
import com.example.springsecurity.responsewrapper.ResponseWrapper;
import com.example.springsecurity.responsewrapper.StatusDescription;
import com.example.springsecurity.service.AuthService;

@RestController
@RequestMapping("auth/v1/")
@CrossOrigin(value = "*")
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping("createUser")
	public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserModel str) {
		ResponseWrapper responseWrapper1 = new ResponseWrapper();
		StatusDescription statusDescription1 = new StatusDescription();
		responseWrapper1 = authService.createUser(str);

		return new ResponseEntity<>(responseWrapper1, responseWrapper1.getHttpStatus());
	}

	@PostMapping("login")
	public ResponseEntity<ResponseWrapper> loginUser(@RequestBody AuthRequestWrapper str) {
		ResponseWrapper responseWrapper1 = new ResponseWrapper();
		StatusDescription statusDescription1 = new StatusDescription();
		responseWrapper1 = authService.loginUser(str);

		return new ResponseEntity<>(responseWrapper1, responseWrapper1.getHttpStatus());
	}
}
