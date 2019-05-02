package com.orangelabs.RestaurantDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.request.AuthenticationRequest;
import com.orangelabs.RestaurantDemo.response.JwtTokenResponse;
import com.orangelabs.RestaurantDemo.response.ResponseMessage;
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
		ResponseMessage response = new ResponseMessage();
		try {
			
			UserEntity foundUser = authService.authenticateUser(request);
			
			UsernamePasswordAuthenticationToken authentication = 
					this.resgisteringAuthenticatedUserInContext(foundUser);
			
			JwtTokenResponse tokenResponse = 
					this.generateTokenWithResponse(foundUser, authentication);			
			
			return new ResponseEntity<JwtTokenResponse>(tokenResponse, HttpStatus.OK);
			
		}catch(Exception error) {
			response.setMessage(error.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    }
	
	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody UserEntity newUser) {
		ResponseMessage response = new ResponseMessage();
		try{
			
			if(newUser.getEmail() == null || newUser.getPassword() == null) {
				response.setMessage("Email or Password is missing");
				return new ResponseEntity<> (response, HttpStatus.CONFLICT);
			}
			
			authService.createUser(newUser);
			response.setMessage("User Created Successfully!");
			
			return new ResponseEntity<> (response, HttpStatus.CREATED);
			
		}catch(Exception error) {
			response.setMessage("This email is already taken");
			return new ResponseEntity<> (response, HttpStatus.CONFLICT);
		}
	}
	
	//////
	
	private UsernamePasswordAuthenticationToken resgisteringAuthenticatedUserInContext(UserEntity user) {
		//Building authentication profile
		JwtAuthenticatedProfile authenticatedUser = JwtAuthenticatedProfile.build(user);
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authenticatedUser.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return authentication;
	}
	
	private JwtTokenResponse generateTokenWithResponse(UserEntity authenticatedUser,
			UsernamePasswordAuthenticationToken authentication) {
		
		String accessToken = authService.generateJWTToken(authenticatedUser.getEmail());		
		
		JwtTokenResponse tokenResponse = 
				new JwtTokenResponse(accessToken, 
						authentication.getPrincipal().toString(), 
						authentication.getAuthorities());
		
		return tokenResponse;
	}
}
