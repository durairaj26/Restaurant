package com.springboot.restaurant.vo;

import lombok.Getter;
import lombok.Setter;

// Handle response for check seat availability 

@Setter
@Getter
public class TableAvailabilityVO {
	private String tableName;
	private int availableSeats;
	private String availableStatus;

	public TableAvailabilityVO(String tableName, int availableSeats, String isAvailable) {
		this.tableName = tableName;
		this.availableSeats = availableSeats;
		this.availableStatus = isAvailable;
	}

	public TableAvailabilityVO() {
		super();
	}

}
