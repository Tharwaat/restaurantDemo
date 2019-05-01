package com.orangelabs.RestaurantDemo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.orangelabs.RestaurantDemo.entity.UserEntity;

import io.jsonwebtoken.JwtException;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtServiceToInject) {
        this.jwtService = jwtServiceToInject;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            String token = (String) authentication.getCredentials();
            String email = jwtService.getEmailFromToken(token);
            System.out.println(token + "   " + email);
            boolean tokenValidated = jwtService.validateToken(token);
            
            if(!tokenValidated) {
            	throw new JwtAuthenticationException("JWT Token is Not Valid");
            }
            
            return new JwtAuthenticatedProfile(email);

        } catch (JwtException ex) {
            log.error(String.format("Invalid JWT Token: %s", ex.getMessage()));
            throw new JwtAuthenticationException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
