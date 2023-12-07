package com.springboot.restaurant.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

// Handle Request and response for Booking entity operation

@Setter
@Getter
public class BookingVO {
	private Long bookingId;
	private String userName;
	private String tableName;
	private String mealTypeName;
	private LocalDate date;
	private int numberOfPersons;
	private boolean canceled;
}
