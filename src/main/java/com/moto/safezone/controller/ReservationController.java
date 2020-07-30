package com.moto.safezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.moto.safezone.dto.FloorToPlaceDto;
import com.moto.safezone.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/app")
public class ReservationController {
	
	@Autowired
	UserService userService;

	@PutMapping("/reserve")
	public boolean reserve(@RequestBody List <String> reserveDates) {
		return userService.reserve(reserveDates);
	}
	
	@DeleteMapping("/reserve")
	public boolean removeReservation(@RequestBody List <String> reserveDates) {
		return userService.removeReservations(reserveDates);
	}
	
	@GetMapping("/reservations/date/{date}")
	public Iterable<FloorToPlaceDto> findAllReservationsByDate(@PathVariable String date){
		return userService.findAllReservationsByDate(date);
	}
}
