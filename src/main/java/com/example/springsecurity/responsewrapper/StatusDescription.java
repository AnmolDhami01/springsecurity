package com.example.springsecurity.responsewrapper;

public class StatusDescription {
	
	private int StatusCode;
	private String StatusDescription;
	public int getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(int statusCode) {
		StatusCode = statusCode;
	}
	public String getStatusDescription() {
		return StatusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		StatusDescription = statusDescription;
	}
	@Override
	public String toString() {
		return "StatusDescription [StatusCode=" + StatusCode + ", StatusDescription=" + StatusDescription + "]";
	}

}
