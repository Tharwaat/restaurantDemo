package com.orangelabs.RestaurantDemo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.TableEntity;
import com.orangelabs.RestaurantDemo.request.NewTableRequest;
import com.orangelabs.RestaurantDemo.response.ResponseMessage;
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
	
	@PostMapping("/tables")
	public ResponseEntity addTable(@RequestBody NewTableRequest newTable) {
		ResponseMessage response = new ResponseMessage();
		
		if(newTable.getTableCapacity() == 0 || newTable.getTableCapacity() == 0) {
			response.setMessage("Invalid request, Table number or Table capacity is missing");
			return 
				new ResponseEntity<>(response,
				HttpStatus.BAD_REQUEST);
		}
		
		tableService.createTable(newTable);
		response.setMessage("Table Created Successfully!");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/tables/available")
	public ResponseEntity<Set<TableEntity>> getAvailableTablesByDate(@RequestParam("date")  String date){		
		Set<TableEntity> availableTables = tableService.getAvailavleTables(date);
		return new ResponseEntity<Set<TableEntity>>(availableTables, HttpStatus.OK);
	}
}
