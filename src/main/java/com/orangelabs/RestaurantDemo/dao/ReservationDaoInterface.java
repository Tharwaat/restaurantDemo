package com.orangelabs.RestaurantDemo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangelabs.RestaurantDemo.entity.ReservationEntity;

public interface ReservationDaoInterface extends JpaRepository<ReservationEntity, Integer> {
	@Query(value="SELECT * FROM reservation WHERE date =  ?1", 
			nativeQuery = true)
	public List<ReservationEntity> getReservedTables(Date availableDate);
}
