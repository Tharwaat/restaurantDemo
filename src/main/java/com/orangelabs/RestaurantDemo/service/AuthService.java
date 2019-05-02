package com.orangelabs.RestaurantDemo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.entity.UserEntity.UserType;
import com.orangelabs.RestaurantDemo.request.AuthenticationRequest;
import com.orangelabs.RestaurantDemo.response.JwtTokenResponse;
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
	
	public String generateJWTToken(String email) {
        String token = jwtService.generateToken(email);
        return token;
    }
	
	public UserEntity authenticateUser(AuthenticationRequest request) {
		try{
			String email = request.getEmail();
			String password = request.getPassword();
			
			UserEntity foundUser = userDaoInterface.findByEmail(email);
			
			if(foundUser == null)
				throw new EntityNotFoundException("Account not Found");
			
			if(!bcryptPasswordEncoder.matches(password, foundUser.getPassword()))
				throw new EntityNotFoundException("Wrong Password!");        	
			
			return foundUser;
			
		}catch(Exception error) {
			throw error;
		}		
	}
}
