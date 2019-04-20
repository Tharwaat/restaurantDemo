package com.orangelabs.RestaurantDemo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class ReservationEntity {
	
	// Columns
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date reservationDate;
	
	@Column(name="persons_number")
	private int personsNumber;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="table_id")
	private TableEntity table;
	
	//Constructors
	public ReservationEntity(Date reservationDate, int personsNumber, UserEntity user, TableEntity table) {	
		this.reservationDate = reservationDate;
		this.personsNumber = personsNumber;
		this.user = user;
		this.table = table;
	}

	public ReservationEntity() {
		
	}

	// Setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getPersonsNumber() {
		return personsNumber;
	}

	public void setPersonsNumber(int personsNumber) {
		this.personsNumber = personsNumber;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TableEntity getTable() {
		return table;
	}

	public void setTable(TableEntity table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "ReservationEntity [id=" + id + ", reservationDate=" + reservationDate + ", personsNumber="
				+ personsNumber + ", user=" + user + ", table=" + table + "]";
	}
	
	
}
