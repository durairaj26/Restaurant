package com.springboot.restaurant.vo;

import java.util.Date;
import com.springboot.restaurant.entity.MealType;
import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingVO {
	private User userId;
	private Tables tableId;
	private MealType mealTypeId;
	private Date date;
	private int numberOfPersons;
	private boolean canceled;

}
