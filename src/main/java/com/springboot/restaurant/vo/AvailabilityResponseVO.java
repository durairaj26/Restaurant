package com.springboot.restaurant.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityResponseVO {
	private String tableName;
	private int availableSeats;
	
	public void calculateAvailableSeats(int seatingCapacity, int numberOfPersons) {
	        this.availableSeats = seatingCapacity - numberOfPersons;
	    }
	
}
