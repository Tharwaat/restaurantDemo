package com.orangelabs.RestaurantDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangelabs.RestaurantDemo.dao.TableDao;
import com.orangelabs.RestaurantDemo.entity.TableEntity;

@Service
public class TableService {
	private TableDao tableDao;
	
	@Autowired
	public TableService(TableDao tableDaoToEnject) {
		this.tableDao = tableDaoToEnject; 
	}
	
	@Transactional
	public List<TableEntity> getTables(){
		return tableDao.getAllTables();
	}
	
	@Transactional
	public void createTable(TableEntity newTable) {
		tableDao.saveTable(newTable);
	}
}
