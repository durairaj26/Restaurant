package com.springboot.restaurant.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant.entity.Booking;
import com.springboot.restaurant.entity.MealType;
import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.vo.AvailabilityResponseVO;
import com.springboot.restaurant.vo.BookingVO;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<BookingVO> findByUserUserId(Long userId);

	Optional<BookingVO> findByDate(Date date);

	Optional<BookingVO> findByMealTypeName(String mealTypeName);

	AvailabilityResponseVO saveAll(AvailabilityResponseVO availabilityResponseVO);

	
}
