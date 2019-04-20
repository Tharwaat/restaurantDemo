package com.orangelabs.RestaurantDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangelabs.RestaurantDemo.dao.TableDaoInterface;
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
	
	@Autowired
	public TableService(TableDaoInterface tableDaoToEnject) {
		this.tablesRepository = tableDaoToEnject; 
	}
	
	public List<TableEntity> getTables(){
		return tablesRepository.findAll();
	}
	
	public void createTable(TableEntity newTable) {
		tablesRepository.save(newTable);
	}
	
}
