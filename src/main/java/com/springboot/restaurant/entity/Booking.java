package com.springboot.restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@jakarta.persistence.Table
@Data
@Setter
@Getter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "table_id")
	private Tables table;

	@ManyToOne
	@JoinColumn(name = "meal_type_id")
	private MealType mealType;

	private Date date;
	private int numberOfPersons;
	private boolean canceled;

}
