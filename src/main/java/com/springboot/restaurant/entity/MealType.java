package com.springboot.restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data
@Setter
@Getter
public class MealType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealTypeId;

    private String mealTypeName;

}
