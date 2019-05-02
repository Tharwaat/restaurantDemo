package com.orangelabs.RestaurantDemo.request;

public class NewReservationRequest {
	private String reservationDate;
	private int personsNumber;
	private int userId;
	private int tableId;
	
	public NewReservationRequest() {
		
	}

	public NewReservationRequest(String reservationDate, int personsNumber, int userId, int tableId) {
		super();
		this.reservationDate = reservationDate;
		this.personsNumber = personsNumber;
		this.userId = userId;
		this.tableId = tableId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getPersonsNumber() {
		return personsNumber;
	}

	public void setPersonsNumber(int personsNumber) {
		this.personsNumber = personsNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	@Override
	public String toString() {
		return "NewReservationRequest [reservationDate=" + reservationDate + ", personsNumber=" + personsNumber
				+ ", userId=" + userId + ", tableId=" + tableId + "]";
	}	
}
