package com.orangelabs.RestaurantDemo.controller;

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
import com.orangelabs.RestaurantDemo.response.ResponseMessage;
import com.orangelabs.RestaurantDemo.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
	private ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationServiceToEnject) {
		this.reservationService = reservationServiceToEnject; 
	}
	
	@GetMapping("/reservations/filter")
	public ResponseEntity<List<ReservationEntity>> getReservationsByDate(@RequestParam("date")  String date){
		List<ReservationEntity> reservations = reservationService.getReservationsByDate(date);
		return new ResponseEntity<List<ReservationEntity>>(reservations, HttpStatus.OK);		
	}
	
	@GetMapping("/reservations")	
	public ResponseEntity<List<ReservationEntity>> getAllReservations(){
		List<ReservationEntity> reservations = reservationService.getReservations();
		return new ResponseEntity<List<ReservationEntity>>(reservations, HttpStatus.OK);
	}
	
	@PostMapping("/reservations")
	public ResponseEntity makeReservation(@RequestBody NewReservationRequest newReservation){
		ResponseMessage response = new ResponseMessage();
		
		if(newReservation.getPersonsNumber() == 0 || newReservation.getReservationDate() == null) {
			response.setMessage("Invalid request, Persons number or Reservation date is missing");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		reservationService.createReservation(newReservation);
		response.setMessage("Reservation's been saved Successfully!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}	
}
