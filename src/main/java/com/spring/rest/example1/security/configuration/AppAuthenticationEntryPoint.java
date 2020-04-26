package com.spring.rest.example1.security.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		authException.printStackTrace();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				authException.getMessage() != null ? authException.getMessage() : "Access Denied.");
	}
}