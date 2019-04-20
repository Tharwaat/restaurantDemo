package com.orangelabs.RestaurantDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orangelabs.RestaurantDemo.entity.ReservationEntity;

public interface ReservationDaoInterface extends JpaRepository<ReservationEntity, Integer> {

}
