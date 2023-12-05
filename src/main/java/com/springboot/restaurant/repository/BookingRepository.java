package com.springboot.restaurant.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	// Find user by UserId

	List<Booking> findByUserUserId(Long userId);

	// Get available seats

	@Query("SELECT td.tableName, td.seatingCapacity, (td.seatingCapacity - COALESCE(SUM(CASE WHEN bd.canceled = true THEN 0 ELSE bd.numberOfPersons END), 0)) AS availableSeats "
			+ "FROM Tables td " + "LEFT JOIN Booking bd ON td.tableId = bd.table.tableId " + "AND bd.date = :userDate "
			+ "AND bd.mealType.mealTypeName = :mealTypeName " + "GROUP BY td.tableId, td.tableName, td.seatingCapacity")
	List<Object[]> getAvailableSeats(@Param("userDate") LocalDate userDate, @Param("mealTypeName") String mealTypeName);

	// Get available seats by table name -> book table check availability

	@Query("SELECT td.tableName, (td.seatingCapacity - COALESCE(SUM(CASE WHEN bd.canceled = true THEN 0 ELSE bd.numberOfPersons END), 0)) AS availableSeats "
			+ "FROM Tables td " + "LEFT JOIN Booking bd ON td.tableId = bd.table.tableId " + "AND bd.date = :userDate "
			+ "AND bd.mealType.mealTypeName = :mealTypeName " + "AND td.tableName = :tableName "
			+ "GROUP BY td.tableId, td.tableName, td.seatingCapacity")
	List<Object[]> getAvailableSeatsByTableName(@Param("userDate") LocalDate userDate,
			@Param("mealTypeName") String mealTypeName, @Param("tableName") String tableName);

	// Cancel booking by booking and user id

	Optional<Booking> findByBookingIdAndUserUserId(Long bookingId, Long userId);

}
