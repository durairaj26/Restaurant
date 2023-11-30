package com.springboot.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant.service.UserService;
import com.springboot.restaurant.vo.AvailabilityRequestVO;
import com.springboot.restaurant.vo.BookingVO;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/booking/listall")
	public ResponseEntity<List<BookingVO>> listBooking(@RequestHeader("userId") Long userId) {
		List<BookingVO> listBookingVO = userService.listBooking(userId);
		return ResponseEntity.ok(listBookingVO);
	}

	@PostMapping("/booking/booktable")
	public ResponseEntity<BookingVO> bookTable(@RequestHeader("userId") Long userId, @RequestBody BookingVO bookingVO) {
		BookingVO bookedTable = userService.bookTable(userId, bookingVO);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookedTable);
	}
	
	@PostMapping("/checkAvailability")
    public ResponseEntity<Map<String, Integer>> checkAvailability(@RequestBody AvailabilityRequestVO request,
                                                                  @RequestHeader("userId") Long userId) {
        Map<String, Integer> availabilityMap = userService.checkAvailability(userId, request);
        return ResponseEntity.ok(availabilityMap);
    }
}
