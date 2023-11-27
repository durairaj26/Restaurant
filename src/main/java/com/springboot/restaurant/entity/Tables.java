package com.springboot.restaurant.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@jakarta.persistence.Table(name = "tables")
@Data
@Setter
@Getter
public class Tables {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tableId;

	private String tableName;
	private int seatingCapacity;

}
