package com.orangelabs.RestaurantDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.service.AuthService;

@RestController
@RequestMapping
public class AuthController {
	
	private AuthService authService;
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	public AuthController(AuthService authServiceToInject, BCryptPasswordEncoder passwordEncoderToInject) {
		this.authService = authServiceToInject;
		this.bcryptPasswordEncoder = passwordEncoderToInject;
	}
	
//	@PostMapping("/login")
//    public ResponseEntity createCustomer(@RequestBody AuthenticationRequest request) {
//        return new ResponseEntity<>(authenticationService.generateJWTToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
//    }
	
	@PostMapping("/register")
	public void registerUser(@RequestBody UserEntity newUser) {
		String encryptedPassword = bcryptPasswordEncoder.encode(newUser.getPassword());
		newUser.setPassword(encryptedPassword);
		System.out.println(newUser);
		authService.createUser(newUser);
	}
}
