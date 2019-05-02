package com.orangelabs.RestaurantDemo.request;

public class NewTableRequest {
	private int tableNumber;
	private int tableCapacity;
	
	public NewTableRequest(int tableNumber, int tableCapacity) {
		super();
		this.tableNumber = tableNumber;
		this.tableCapacity = tableCapacity;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	@Override
	public String toString() {
		return "NewTableRequest [tableNumber=" + tableNumber + ", tableCapacity=" + tableCapacity + "]";
	}
}
