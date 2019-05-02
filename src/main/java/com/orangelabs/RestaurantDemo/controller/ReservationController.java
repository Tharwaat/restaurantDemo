package com.orangelabs.RestaurantDemo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orangelabs.RestaurantDemo.entity.ReservationEntity;
import com.orangelabs.RestaurantDemo.request.NewReservationRequest;
import com.orangelabs.RestaurantDemo.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
	private ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationServiceToEnject) {
		this.reservationService = reservationServiceToEnject; 
	}
	
	@GetMapping("/reservations")	
	public ResponseEntity<List<ReservationEntity>> getAllReservations(){
		List<ReservationEntity> reservations = reservationService.getReservations();
		return new ResponseEntity<List<ReservationEntity>>(reservations, HttpStatus.OK);
	}
	
	@PostMapping("/reservations")
	public ResponseEntity<String> makeReservation(@RequestBody NewReservationRequest newReservation){
		reservationService.createReservation(newReservation);
		return new ResponseEntity<String>("Reservation's been saved Successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/reservatoions/filter")
	public ResponseEntity<List<ReservationEntity>> getReservationsByDate(@RequestParam("date")  String date){
		Date dateToSearchWith = java.sql.Date.valueOf(date);
		List<ReservationEntity> reservations = reservationService.getReservationsByDate(dateToSearchWith);
		return new ResponseEntity<List<ReservationEntity>>(reservations, HttpStatus.OK);		
	}
}
