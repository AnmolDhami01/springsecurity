package com.example.springsecurity.responsewrapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.springsecurity.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseWrapper implements Serializable {

	StatusDescription statusDescription;
	
	UserModel user;
	@JsonIgnore
	HttpStatus httpStatus;
	String token;
	public StatusDescription getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public UserModel getUser() {
		return user;
	}
	@Override
	public String toString() {
		return "ResponseWrapper [statusDescription=" + statusDescription + ", user=" + user + ", httpStatus="
				+ httpStatus + ", token=" + token + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
