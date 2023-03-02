package com.example.springsecurity.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springsecurity.helper.AuthUtils;
import com.example.springsecurity.helper.JwtUtil;
import com.example.springsecurity.model.JwtModel;
import com.example.springsecurity.model.UserModel;
import com.example.springsecurity.repo.JwtModelRepo;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.requestwrapper.AuthRequestWrapper;
import com.example.springsecurity.responsewrapper.ResponseWrapper;
import com.example.springsecurity.responsewrapper.StatusDescription;
import com.example.springsecurity.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthUtils utils;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	JwtModelRepo jwtModelRepo;

	@Override
	public ResponseWrapper createUser(UserModel str) {

		ResponseWrapper responseWrapper1 = new ResponseWrapper();
		StatusDescription statusDescription1 = new StatusDescription();
		try {

			System.out.println(str);

			if ((str.getUsername() == null && str.getUsername() == "")
					|| (str.getPassword() == null && str.getPassword() == "")
					|| (str.getEmail() == null && str.getEmail() == "")
					|| (str.getRole() == null && str.getRole() == "")) {
				statusDescription1.setStatusCode(400);
				statusDescription1.setStatusDescription("Bad Request");
				responseWrapper1.setStatusDescription(statusDescription1);
				responseWrapper1.setHttpStatus(HttpStatus.BAD_REQUEST);
				return responseWrapper1;
			}

			UserModel findByUsername = this.userRepo.findByUsername(str.getUsername());

			if (findByUsername != null) {
				statusDescription1.setStatusCode(409);
				statusDescription1.setStatusDescription("User this username already exist");
				responseWrapper1.setStatusDescription(statusDescription1);
				responseWrapper1.setHttpStatus(HttpStatus.OK);
				return responseWrapper1;
			}

			str.setPassword(this.utils.generatePassword(str.getPassword()));

			this.userRepo.save(str);

			statusDescription1.setStatusCode(200);
			statusDescription1.setStatusDescription("Success");
			responseWrapper1.setStatusDescription(statusDescription1);
			responseWrapper1.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			statusDescription1.setStatusCode(500);
			statusDescription1.setStatusDescription("Internal Server Error");
			responseWrapper1.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			responseWrapper1.setStatusDescription(statusDescription1);
		}

		return responseWrapper1;
	}

	@Override
	public ResponseWrapper loginUser(AuthRequestWrapper str) {

		ResponseWrapper responseWrapper1 = new ResponseWrapper();
		StatusDescription statusDescription1 = new StatusDescription();
		JwtModel jwtModel = new JwtModel();
		try {

			if ((str.getUsername() == null && str.getUsername() == "")
					|| (str.getPassword() == null && str.getPassword() == "")) {
				statusDescription1.setStatusCode(400);
				statusDescription1.setStatusDescription("Bad Request");
				responseWrapper1.setStatusDescription(statusDescription1);
				responseWrapper1.setHttpStatus(HttpStatus.BAD_REQUEST);
				return responseWrapper1;
			}

			UserModel findByUsername = this.userRepo.findByUsername(str.getUsername());

			if (findByUsername == null) {
				statusDescription1.setStatusCode(15);
				statusDescription1.setStatusDescription("No User Found");
				responseWrapper1.setStatusDescription(statusDescription1);
				responseWrapper1.setHttpStatus(HttpStatus.OK);
				return responseWrapper1;
			}

			String generatePassword = this.utils.generatePassword(str.getPassword());

			if (!generatePassword.equals(findByUsername.getPassword())) {
				statusDescription1.setStatusCode(15);
				statusDescription1.setStatusDescription("Password Incorrect");
				responseWrapper1.setStatusDescription(statusDescription1);
				responseWrapper1.setHttpStatus(HttpStatus.OK);
				return responseWrapper1;
			}

			String generateToken = this.jwtUtil.generateTokenAuth(findByUsername);

			JwtModel findByUserId = this.jwtModelRepo.findByUserId(findByUsername.getId());

			if (findByUserId == null) {

				jwtModel.setUser(findByUsername);
				jwtModel.setUsername(str.getUsername());
				jwtModel.setExpiryDate(new Date());
				jwtModel.setJwtToken(generateToken);
				JwtModel savedToken = this.jwtModelRepo.save(jwtModel);
				findByUsername.setUserTokenDetails(savedToken);
			} else {
				findByUserId.setExpiryDate(new Date());
				findByUserId.setJwtToken(generateToken);
				JwtModel savedToken = this.jwtModelRepo.save(findByUserId);
				findByUsername.setUserTokenDetails(savedToken);
			}

			responseWrapper1.setUser(findByUsername);
			statusDescription1.setStatusCode(200);
			statusDescription1.setStatusDescription("Success");
			responseWrapper1.setStatusDescription(statusDescription1);
			responseWrapper1.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			statusDescription1.setStatusCode(500);
			statusDescription1.setStatusDescription("Internal Server Error");
			responseWrapper1.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			responseWrapper1.setStatusDescription(statusDescription1);
		}

		return responseWrapper1;
	}

}
