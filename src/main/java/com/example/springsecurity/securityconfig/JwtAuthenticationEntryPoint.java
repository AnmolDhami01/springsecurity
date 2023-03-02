package com.example.springsecurity.securityconfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.jose4j.json.internal.json_simple.JSONObject;
@Service
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		System.out.println("IIIIIIIII"+request);
//		response.sendError(401, "unauthorized");
		
		String statusCode = String.valueOf(request.getAttribute("statusCode"));
		String statusMessage = String.valueOf(request.getAttribute("statusMessage"));
		final Map<String, Object> body = new HashMap<>();

		if (statusCode.equals("null")) {

			JSONObject status = new JSONObject();
			status.put("statusCode", ConstantManager.unauthorizedAccess.getStatusCode());
			status.put("statusMessage", ConstantManager.unauthorizedAccess.getDescription());

			body.put("statusDescription", status);

			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), body);

		} else {

			JSONObject status = new JSONObject();
			status.put("statusCode", statusCode);
			status.put("statusMessage", statusMessage);

			body.put("statusDescription", status);

			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), body);

		}

	}

}
