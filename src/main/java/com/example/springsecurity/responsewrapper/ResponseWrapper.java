package com.example.springsecurity.responsewrapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.springsecurity.model.UserModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.onlyjavatech.springbootproject.model.AuthourModel;
import com.onlyjavatech.springbootproject.model.BooksModel;
import com.onlyjavatech.springbootproject.model.Students;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseWrapper implements Serializable {

	StatusDescription statusDescription;
	
	UserModel user;

}
