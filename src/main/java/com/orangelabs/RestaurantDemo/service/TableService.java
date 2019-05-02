package com.orangelabs.RestaurantDemo.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.orangelabs.RestaurantDemo.dao.ReservationDaoInterface;
import com.orangelabs.RestaurantDemo.dao.TableDaoInterface;
//import com.orangelabs.RestaurantDemo.entity.ReservationEntity;
import com.orangelabs.RestaurantDemo.entity.TableEntity;
import com.orangelabs.RestaurantDemo.request.NewTableRequest;

@Service
public class TableService {

	private TableDaoInterface tablesRepository;
	//private ReservationDaoInterface reservationRepository;

	@Autowired
	public TableService(TableDaoInterface tableDaoToInject) {
			//, ReservationDaoInterface reservationDaoToInject) {
		this.tablesRepository = tableDaoToInject;
		//this.reservationRepository = reservationDaoToInject;
	}

	public List<TableEntity> getTables() {
		try {
			
			return tablesRepository.findAll();
			
		} catch (Exception error) {
			throw error;
		}
	}

	public void createTable(NewTableRequest newTable) {
		try {
			
			TableEntity tableToSave = new TableEntity(newTable.getTableCapacity(), newTable.getTableNumber());
			tablesRepository.save(tableToSave);
			
		} catch (Exception error) {
			throw error;
		}
	}

	public Set<TableEntity> getAvailavleTables(String date) {
		try {
			
			Date availableDate = java.sql.Date.valueOf(date);
			Set<TableEntity> availableTables = tablesRepository.getAvailableTables(availableDate);
			
			return availableTables;
			
		} catch (Exception error) {
			throw error;
		}
	}

}
