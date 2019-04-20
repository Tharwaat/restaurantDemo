package com.orangelabs.RestaurantDemo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<TableEntity>> getAllTables(){
		List<TableEntity> tables = tableService.getTables();
		return new ResponseEntity<List<TableEntity>>(tables, HttpStatus.OK);
	}
	
	@PostMapping("/tables/new")
	public TableEntity addTable(@RequestBody TableEntity newTable) {
		newTable.setId(0);
		tableService.createTable(newTable);
		
		return newTable;
	}
	
	@GetMapping("/tables/available")
	public List<TableEntity> getAvailableTables(@RequestParam("date")  String date){
		Date newDate = java.sql.Date.valueOf(date);
		return tableService.getAvailavleTables(newDate);
	}
}
