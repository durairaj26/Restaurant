package com.springboot.restaurant.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@jakarta.persistence.Table
@Data
@Setter
@Getter
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "table_id")
	private Tables table;

	@ManyToOne
	@JoinColumn(name = "meal_type_id")
	private MealType mealType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private int numberOfPersons;
	private boolean canceled;

}
