package com.springboot.restaurant.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.restaurant.entity.MealType;

@Repository
public interface MealTypeRepository extends JpaRepository<MealType, Long>{

	Optional<MealType> findByMealTypeName(String mealTypeName);

}
