/**
 * 
 */
package com.spring.rest.example1.security.filter.configuration;

import static com.spring.rest.example1.common.util.CodeUtility.isEmpty;
import static com.spring.rest.example1.common.util.CodeUtility.isNotEmpty;

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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.rest.example1.security.util.JwtUtil;
import com.spring.rest.example1.service.MyAppUserDetailService;

/**
 * @author tarkhand
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyAppUserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if (isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			username = jwtUtil.extractUserName(token);
		}
		if (isNotEmpty(username) && isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
