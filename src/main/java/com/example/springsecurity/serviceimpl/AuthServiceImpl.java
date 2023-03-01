package com.example.springsecurity.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springsecurity.model.UserModel;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.responsewrapper.ResponseWrapper;
import com.example.springsecurity.responsewrapper.StatusDescription;
import com.example.springsecurity.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	UserRepo userRepo;

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

}
