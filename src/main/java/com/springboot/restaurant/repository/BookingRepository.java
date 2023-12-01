package com.springboot.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant.entity.Booking;
import com.springboot.restaurant.vo.BookingVO;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<BookingVO> findByUserUserId(Long userId);

	@Query("SELECT td.tableName, td.seatingCapacity, (td.seatingCapacity - COALESCE(SUM(bd.numberOfPersons), 0)) AS availableSeats "
			+ "FROM Tables td " + "LEFT JOIN Booking bd ON td.tableId = bd.table.tableId " + "AND bd.date = :userDate "
			+ "AND bd.mealType.mealTypeName = :mealTypeName "
			+ "GROUP BY td.tableId, td.tableName, td.seatingCapacity, td.seatingCapacity")
	List<Object[]> getAvailableSeats(@Param("userDate") LocalDateTime userDate,
			@Param("mealTypeName") String mealTypeName);

}
