package com.orangelabs.RestaurantDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangelabs.RestaurantDemo.entity.UserEntity;

public interface UserDaoInterface extends JpaRepository<UserEntity, Integer> {
	@Query(value="SELECT * FROM user where email = ?1", 
			nativeQuery = true)
	UserEntity findByEmail(String email);
}
