package com.example.springsecurity.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.springsecurity.model.UserModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtUtil {
	@Autowired
	private HttpServletRequest request;

	private String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		try {

			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		} catch (SignatureException e) {

			request.setAttribute("statusCode", "15");
			request.setAttribute("statusMessage", "Invalid Jwt Sinature");
		} catch (MalformedJwtException e) {

			request.setAttribute("statusCode", "15");
			request.setAttribute("statusMessage", "Jwt Malformed");
		} catch (ExpiredJwtException e) {

			request.setAttribute("statusCode", "15");
			request.setAttribute("statusMessage", "Jwt Token Expired");
		} catch (UnsupportedJwtException e) {

			request.setAttribute("statusCode", "15");
			request.setAttribute("statusMessage", "Unsupported Jwt");
		} catch (IllegalArgumentException e) {

			request.setAttribute("statusCode", "15");
			request.setAttribute("statusMessage", "Illegal Argument");
		}

		return null;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	public String generateTokenAuth(UserModel userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}