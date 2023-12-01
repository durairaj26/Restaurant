package com.springboot.restaurant.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingVO {
	private String userName;
	private String tableName;
	private String mealTypeName;

	private Date date;

	private int numberOfPersons;
	private boolean canceled;
}
