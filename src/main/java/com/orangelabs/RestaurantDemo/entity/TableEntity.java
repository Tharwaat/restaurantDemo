package com.orangelabs.RestaurantDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table")
public class TableEntity {
	
	// Columns
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="table_capacity")
	private int tableCapacity;
	
	@Column(name="table_number")
	private int tableNumber;
	
	// Constructors
	public TableEntity(int tableCapacity, int tableNumber) {
		this.tableCapacity = tableCapacity;
		this.tableNumber = tableNumber;
	}
	
	// Setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	// Viewing object
	@Override
	public String toString() {
		return "TableEntity [id=" + id + ", tableCapacity=" + tableCapacity + ", tableNumber=" + tableNumber + "]";
	}

	
}
