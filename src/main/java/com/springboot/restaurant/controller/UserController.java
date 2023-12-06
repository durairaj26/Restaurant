package com.springboot.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant.service.UserService;
import com.springboot.restaurant.vo.BookingRequestVO;
import com.springboot.restaurant.vo.BookingVO;
import com.springboot.restaurant.vo.TableAvailabilityVO;

@RestController
@RequestMapping(path = "/user", produces = { "application/json" }, consumes = { "application/json" })
public class UserController {

	@Autowired
	UserService userService;

	// List all booking based on user by userId

	@GetMapping("/booking/listall")
	public ResponseEntity<List<BookingVO>> listBooking(@RequestHeader("userId") Long userId) {
		List<BookingVO> listBookingVO = userService.listBooking(userId);
		return ResponseEntity.ok(listBookingVO);
	}

	// Book table by user details

	@PostMapping("/booking/booktable")
	public ResponseEntity<BookingVO> bookTable(@RequestHeader("userId") Long userId, @RequestBody BookingVO bookingVO) {
		BookingVO bookedTable = userService.bookTable(userId, bookingVO);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookedTable);
	}

	// Check available seats with table name

	@PostMapping("/booking/availableseats")
	public ResponseEntity<List<TableAvailabilityVO>> getAvailableSeats(@RequestBody BookingRequestVO bookingRequest) {
		List<TableAvailabilityVO> availableSeats = userService.getAvailableSeats(bookingRequest);
		return ResponseEntity.ok(availableSeats);
	}

	// Cancel booking

	@PostMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<String> cancelBooking(@RequestHeader Long userId, @PathVariable Long bookingId) {
		userService.cancelBooking(userId, bookingId);
		return new ResponseEntity<>("Booking successfully Canceled!", HttpStatus.OK);

	}

	// Add extra persons

	@PutMapping("/booking/addPerson/{bookingId}")
	public ResponseEntity<BookingVO> addPersons(@RequestBody BookingVO bookingVO, @RequestHeader Long userId,
			@PathVariable Long bookingId) {

		BookingVO addedPersons = userService.addPersons(bookingVO, userId, bookingId);
		return ResponseEntity.status(HttpStatus.OK).body(addedPersons);
	}

}
