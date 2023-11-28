package com.springboot.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.restaurant.service.UserService;
import com.springboot.restaurant.vo.BookingVO;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/booking/list")
	public ResponseEntity<BookingVO> listBooking(@RequestBody BookingVO bookingVO){
		BookingVO listBookingVO=userService.listBooking(bookingVO);
		return ResponseEntity.status(HttpStatus.OK).body(listBookingVO);
	}

}
