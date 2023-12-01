package com.springboot.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="meal_type")
@Data
@Setter
@Getter
public class MealType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealTypeId;

	@Column(name = "meal_type_name")
    private String mealTypeName;

}
