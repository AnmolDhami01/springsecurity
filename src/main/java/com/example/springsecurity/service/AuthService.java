package com.example.springsecurity.service;


import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsecurity.model.UserModel;
import com.example.springsecurity.responsewrapper.ResponseWrapper;

public interface AuthService {

	 ResponseWrapper createUser(@RequestBody UserModel str);
}
