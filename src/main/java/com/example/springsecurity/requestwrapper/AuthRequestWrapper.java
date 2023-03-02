package com.example.springsecurity.requestwrapper;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
@Data
@ToString
public class AuthRequestWrapper {
	@NotEmpty(message = "username is required.")
	String username;
	@NotEmpty(message = "password is required.")
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequestWrapper [username=" + username + ", password=" + password + "]";
	}

	public AuthRequestWrapper(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
