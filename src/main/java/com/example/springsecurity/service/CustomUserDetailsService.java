package com.example.springsecurity.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.model.UserModel;
import com.example.springsecurity.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserModel findByUsername = this.userrepo.findByUsername(username);
		
		System.out.println("IIIIIIIIIIIIIIIIIII"+findByUsername);

		if (findByUsername == null) {
			throw new UsernameNotFoundException("User Not Found");
		} else {

			return new CustomUserDetails(findByUsername);
		}

//		if (username.equals("Durgesh")) {
//			return new User("Durgesh", "password", new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User Not Found");
//		}

	}

}
