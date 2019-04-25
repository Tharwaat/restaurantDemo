package com.orangelabs.RestaurantDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.UserEntity;

@Service
public class AuthService {
	private UserDaoInterface userDaoInterface;
	
	@Autowired
	public AuthService(UserDaoInterface userDaoToInject) {
		this.userDaoInterface = userDaoToInject;
	}
	
	public void createUser(UserEntity newUser) {
		userDaoInterface.save(newUser);
	}
}
