package com.orangelabs.RestaurantDemo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.ReservationDaoInterface;
import com.orangelabs.RestaurantDemo.dao.TableDaoInterface;
import com.orangelabs.RestaurantDemo.dao.UserDaoInterface;
import com.orangelabs.RestaurantDemo.entity.ReservationEntity;
import com.orangelabs.RestaurantDemo.entity.TableEntity;
import com.orangelabs.RestaurantDemo.entity.UserEntity;
import com.orangelabs.RestaurantDemo.request.NewReservationRequest;

@Service
public class ReservationService {
	
	private TableDaoInterface tablesRepository;
	private UserDaoInterface userRepository;
	private ReservationDaoInterface reservationRepository;
	
	@Autowired
	public ReservationService(TableDaoInterface tableDaoToInject, 
			ReservationDaoInterface reservationDaoToInject,
			UserDaoInterface userDaoToInject) {
		this.tablesRepository = tableDaoToInject;
		this.reservationRepository = reservationDaoToInject;
		this.userRepository = userDaoToInject;
	}
	
	public List<ReservationEntity> getReservations(){
		return reservationRepository.findAll();
	}
	
	public List<ReservationEntity> getReservationsByDate(Date reservationDate){
		return reservationRepository.getReservedTables(reservationDate);
	}
	
	public void createReservation(NewReservationRequest newReservation) {
		try {
			ReservationEntity structuredReservationToSave =
					this.restructNewReservationFromReservationRequest(newReservation);
			reservationRepository.save(structuredReservationToSave);
		}catch(Exception e) {
			throw e;
		}
	}
	
	private ReservationEntity restructNewReservationFromReservationRequest(NewReservationRequest newReservation) {
		try{
			UserEntity userWhoReserved = userRepository.getOne(newReservation.getUserId());		
			TableEntity reservedTable = tablesRepository.getOne(newReservation.getTableId());		
			int numberOfPersons = newReservation.getPersonsNumber();		
			Date dateToReserveWith = java.sql.Date.valueOf(newReservation.getReservationDate());
			
			ReservationEntity reservationToSave = 
					new ReservationEntity(dateToReserveWith, numberOfPersons, userWhoReserved, reservedTable);
			return reservationToSave;
		}catch(Exception e) {
			throw e;
		}	
	}
}
