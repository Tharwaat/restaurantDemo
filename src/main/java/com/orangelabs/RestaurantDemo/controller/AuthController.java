package com.orangelabs.RestaurantDemo.controller;

import java.util.List;

import org.hibernate.MappingException;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.ValueVisitor;
import org.hibernate.type.CollectionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.request.AuthenticationRequest;
import com.orangelabs.RestaurantDemo.response.JwtTokenResponse;
import com.orangelabs.RestaurantDemo.security.JwtAuthenticatedProfile;
import com.orangelabs.RestaurantDemo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthService authService;
	
	public AuthController(AuthService authServiceToInject) {
		this.authService = authServiceToInject;
	}
	
	@PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request) {
		UserEntity resultedUser = authService.checkUserExistence(request.getEmail(), request.getPassword());
		JwtAuthenticatedProfile authenticatedUser = JwtAuthenticatedProfile.build(resultedUser);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(resultedUser.getEmail(), resultedUser.getPassword(), authenticatedUser.getAuthorities());
		System.out.println(authentication);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = authService.generateJWTToken(resultedUser.getEmail());		
		
		JwtTokenResponse tokenResponse = new JwtTokenResponse(accessToken, authentication.getPrincipal().toString(), authentication.getAuthorities());
		
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
	
	@PostMapping("/register")
	public void registerUser(@RequestBody UserEntity newUser) {
		authService.createUser(newUser);
	}
}
