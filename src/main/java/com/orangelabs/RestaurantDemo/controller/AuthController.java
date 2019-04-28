package com.orangelabs.RestaurantDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.request.AuthenticationRequest;
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
        return new ResponseEntity<>(authService.generateJWTToken(request.getEmail(), request.getPassword()), HttpStatus.OK);
    }
	
	@PostMapping("/register")
	public void registerUser(@RequestBody UserEntity newUser) {
		authService.createUser(newUser);
	}
}
