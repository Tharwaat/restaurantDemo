package com.orangelabs.RestaurantDemo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.ReservationDaoInterface;
import com.orangelabs.RestaurantDemo.dao.TableDaoInterface;
import com.orangelabs.RestaurantDemo.entity.ReservationEntity;
import com.orangelabs.RestaurantDemo.entity.TableEntity;

@Service
public class ReservationService {
	
	private TableDaoInterface tablesRepository;
	private ReservationDaoInterface reservationRepository;
	
	@Autowired
	public ReservationService(TableDaoInterface tableDaoToInject, ReservationDaoInterface reservationDaoToInject) {
		this.tablesRepository = tableDaoToInject;
		this.reservationRepository = reservationDaoToInject;
	}
	
	public List<ReservationEntity> getReservations(){
		return reservationRepository.findAll();
	}
	
	public List<ReservationEntity> getReservationsByDate(Date reservationDate){
		return reservationRepository.getReservedTables(reservationDate);
	}
}
