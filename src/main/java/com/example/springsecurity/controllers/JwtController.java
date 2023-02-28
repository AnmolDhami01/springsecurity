package com.example.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.JwtRequest;
import com.example.springsecurity.JwtResponse;
import com.example.springsecurity.helper.JwtUtil;
import com.example.springsecurity.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> gernateToken(@RequestBody JwtRequest jwtReq){
		System.out.println(jwtReq);
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtReq.getUsername(), jwtReq.getPassword()));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		UserDetails loadUserByUsername = this.customUserDetailsService.loadUserByUsername(jwtReq.getUsername());
		
		String generateToken = this.jwtUtil.generateToken(loadUserByUsername);
		return  ResponseEntity.ok(new JwtResponse(generateToken));
	}

}
