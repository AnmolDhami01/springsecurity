package com.example.springsecurity.helper;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

@Service
public class AuthUtils {

	public String generatePassword(String password) {
		String encrypPass = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			byte[] messageDigest = md.digest(password.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return encrypPass;
	}
}
