package com.orangelabs.RestaurantDemo.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.entity.UserEntity.UserType;
import com.orangelabs.RestaurantDemo.response.JWTTokenResponse;
import com.orangelabs.RestaurantDemo.security.JwtService;

@Service
public class AuthService {
	private UserDaoInterface userDaoInterface;
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	private JwtService jwtService;
	
	@Autowired
	public AuthService(UserDaoInterface userDaoToInject, 
			BCryptPasswordEncoder passwordEncoderToInject,
			JwtService jwtServiceToInject) {
		this.userDaoInterface = userDaoToInject;
		this.bcryptPasswordEncoder = passwordEncoderToInject;
		this.jwtService = jwtServiceToInject;
	}
	
	public void createUser(UserEntity newUser) {
		String encryptedPassword = bcryptPasswordEncoder.encode(newUser.getPassword());
		newUser.setPassword(encryptedPassword);
		newUser.setRole(UserType.USER);
		userDaoInterface.save(newUser);
	}
	
	public JWTTokenResponse generateJWTToken(String email, String password) {
		UserEntity foundUser = userDaoInterface.findByEmail(email);
        if(foundUser == null) 
        	new EntityNotFoundException("Account not found");
        
        if(!bcryptPasswordEncoder.matches(password, foundUser.getPassword()))
        	System.out.println("Wrong password");
        
        String token = jwtService.generateToken(email);
        return new JWTTokenResponse(token);
    }
}
