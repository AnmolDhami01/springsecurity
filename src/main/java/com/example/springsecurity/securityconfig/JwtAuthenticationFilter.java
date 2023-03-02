package com.example.springsecurity.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springsecurity.helper.JwtUtil;
import com.example.springsecurity.model.JwtModel;
import com.example.springsecurity.repo.JwtModelRepo;
import com.example.springsecurity.service.CustomUserDetailsService;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JwtModelRepo jwtModelRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String headerToken = request.getHeader("Authorization");
		String username;
		String jwtToken;
		String statusCode = String.valueOf(request.getAttribute("statusCode"));
		System.out.println(statusCode);
		if (headerToken != null && headerToken.startsWith("Bearer ")) {
			
			jwtToken = headerToken.substring(7);
			
			try {
				System.out.println("LLLLLLLLL");

				username = this.jwtUtil.extractUsername(jwtToken);
				
				
				
			

				
				UserDetails loadUserByUsername = this.customUserDetailsService.loadUserByUsername(username);
				
				Boolean validateToken = this.jwtUtil.validateToken(jwtToken, loadUserByUsername);
				
				JwtModel findByUsername = this.jwtModelRepo.findByUsername(username);
				System.out.println("OOOOOOOOOOO"+findByUsername);
				
				if(findByUsername == null) {
					filterChain.doFilter(request, response);
				}

				
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							loadUserByUsername, null, loadUserByUsername.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				} else {
					System.out.println("Token not Validated");
				}

			} catch (Exception e) {
//				e.getClass();
				
				e.printStackTrace();
			}
			
		}
		filterChain.doFilter(request, response);

	}

}
