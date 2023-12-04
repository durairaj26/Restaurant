package com.springboot.restaurant.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

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
