package com.springboot.restaurant.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingRequestVO {
	private String date;
	private String mealTypeName;

	public BookingRequestVO(String date, String mealTypeName) {
		this.date = date;
		this.mealTypeName = mealTypeName;
	}

	public BookingRequestVO() {
		super();
	}

}
