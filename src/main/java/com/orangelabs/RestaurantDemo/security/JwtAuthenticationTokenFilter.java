package com.orangelabs.RestaurantDemo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.UserEntity;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserDaoInterface userService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
    		throws ServletException, IOException {
    	
    	try {
    		String jwt = getJwt(request);
    		
    		if (jwt != null && jwtService.validateJwtToken(jwt)) {
    			
    	        String email = jwtService.getEmailFromToken(jwt);
    	 
    	        UserEntity authenticatedUser = userService.findByEmail(email);
    	        JwtAuthenticatedProfile jwtAuthenticatedUser = JwtAuthenticatedProfile.build(authenticatedUser);
    	        
    	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
    	        		jwtAuthenticatedUser, null, jwtAuthenticatedUser.getAuthorities());
    	        
    	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    	 
    	        SecurityContextHolder.getContext().setAuthentication(authentication);
    	    }
    		
            chain.doFilter(request, response);
            
    	}catch (Exception error) {
    		logger.error("Can not set user authentication -> Message: {}", error);
        }
        
    }
    
    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(this.tokenHeader);
     
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
          return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
