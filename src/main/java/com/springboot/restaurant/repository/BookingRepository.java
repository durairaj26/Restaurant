package com.springboot.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant.entity.Booking;
import com.springboot.restaurant.vo.BookingVO;
import com.springboot.restaurant.vo.TableAvailabilityVO;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<BookingVO> findByUserUserId(Long userId);
	
	@Query(value = "WITH TableDetails AS (SELECT t.table_id, t.table_name, t.seating_capacity, t.seating_capacity AS initial_available_seats FROM Tables t), "
	        + "BookingDetails AS (SELECT b.booking_id, b.user_id, b.table_id, b.meal_type_id, b.date, b.number_of_persons, t.seating_capacity, t.table_name "
	        + "FROM booking b FULL JOIN Tables t ON b.table_id = t.table_id WHERE b.date = CAST(:userDate AS TIMESTAMP) AND b.meal_type_id = "
	        + "(SELECT meal_type_id FROM meal_type WHERE meal_type_name = :mealTypeName)) "
	        + "SELECT td.table_name, td.seating_capacity, (td.initial_available_seats - COALESCE(SUM(bd.number_of_persons), 0)) AS available_seats "
	        + "FROM TableDetails td LEFT JOIN BookingDetails bd ON td.table_id = bd.table_id "
	        + "GROUP BY td.table_id, td.table_name, td.seating_capacity, td.initial_available_seats", nativeQuery = true)
	List<TableAvailabilityVO> getAvailableSeats(@Param("userDate") LocalDateTime userDate, @Param("mealTypeName") String mealTypeName);

}
