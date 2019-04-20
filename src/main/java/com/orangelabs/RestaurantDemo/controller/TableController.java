package com.orangelabs.RestaurantDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.dao.TableDao;
import com.orangelabs.RestaurantDemo.entity.TableEntity;
import com.orangelabs.RestaurantDemo.service.TableService;

@RestController
@RequestMapping("/api")
public class TableController {
	private TableService tableService;
	
	@Autowired
	public TableController(TableService tableServiceToEnject) {
		this.tableService = tableServiceToEnject; 
	}
	
	@GetMapping("/tables")
	public List<TableEntity> getAllTables(){
		return tableService.getTables();
	}
}
