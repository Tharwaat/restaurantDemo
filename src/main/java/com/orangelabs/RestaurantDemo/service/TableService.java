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
public class TableService {
//	private TableDao tableDao;
//	
//	@Autowired
//	public TableService(TableDao tableDaoToEnject) {
//		this.tableDao = tableDaoToEnject; 
//	}
//	
	private TableDaoInterface tablesRepository;
	private ReservationDaoInterface reservationRepository;
	
	@Autowired
	public TableService(TableDaoInterface tableDaoToInject, ReservationDaoInterface reservationDaoToInject) {
		this.tablesRepository = tableDaoToInject;
		this.reservationRepository = reservationDaoToInject;
	}
	
	public List<TableEntity> getTables(){
		return tablesRepository.findAll();
	}
	
	public void createTable(TableEntity newTable) {
		tablesRepository.save(newTable);
	}
	
	public List<TableEntity>  getAvailavleTables(Date availableDate) {
		List<TableEntity> availableTables = tablesRepository.getAvailableTables(availableDate); 
		List<ReservationEntity> reservations = reservationRepository.getReservedTables(availableDate);
		availableTables = RemoveDuplicateReservedTables(availableTables, reservations);
		return availableTables;
	}
	
	private List<TableEntity> RemoveDuplicateReservedTables(List<TableEntity> tables, List<ReservationEntity> reservations) {
		List<TableEntity> modifiedTables = tables;
		for(TableEntity table : modifiedTables) {
			for(ReservationEntity reservation : reservations) {
				if(table == reservation.getTable()) {
					modifiedTables.remove(table);
					break;
				}
			}
		}
		return modifiedTables;
	}
	
}
