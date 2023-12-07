package com.springboot.restaurant.vo;

import lombok.Getter;
import lombok.Setter;

//Handle request and response for tables entity operation

@Setter
@Getter
public class TableVO {
	private Long tableId;
	private String tableName;
	private int seatingCapacity;

}
