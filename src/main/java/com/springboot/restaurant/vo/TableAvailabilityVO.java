package com.springboot.restaurant.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TableAvailabilityVO {
	private String tableName;
	private int availableSeats;

	public TableAvailabilityVO(String tableName, int seatingCapacity, int availableSeats) {
		this.tableName = tableName;
		this.availableSeats = availableSeats;
	}

	public TableAvailabilityVO() {
		super();
	}

}
