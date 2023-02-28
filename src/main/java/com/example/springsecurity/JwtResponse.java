package com.example.springsecurity;

public class JwtResponse {
	String token;

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}


	

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setToken(String token) {
		this.token = token;
	}

}