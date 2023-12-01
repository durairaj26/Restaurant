package com.springboot.restaurant.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TableAvailabilityVO {
	private String tableName;
    private int seatingCapacity;
    private int availableSeats;

}
