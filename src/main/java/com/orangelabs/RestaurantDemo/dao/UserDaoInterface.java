package com.orangelabs.RestaurantDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orangelabs.RestaurantDemo.entity.UserEntity;

public interface UserDaoInterface extends JpaRepository<UserEntity, Integer> {
	UserEntity findByEmail(String email);
}
