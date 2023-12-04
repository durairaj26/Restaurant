package com.springboot.restaurant.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant.entity.Booking;
import com.springboot.restaurant.entity.MealType;
import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.entity.User;
import com.springboot.restaurant.repository.BookingRepository;
import com.springboot.restaurant.repository.MealTypeRepository;
import com.springboot.restaurant.repository.TableRepository;
import com.springboot.restaurant.repository.UserRepository;
import com.springboot.restaurant.vo.BookingRequestVO;
import com.springboot.restaurant.vo.BookingVO;
import com.springboot.restaurant.vo.TableAvailabilityVO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	TableRepository tableRepository;

	@Autowired
	MealTypeRepository mealTypeRepository;

	@Autowired
	UserRepository userRepository;

	// List all booking based on user by userId

	public List<BookingVO> listBooking(Long userId) {
		return bookingRepository.findByUserUserId(userId).stream()
				.map(booking -> modelMapper.map(booking, BookingVO.class)).collect(Collectors.toList());
	}

	// Book table by user details

	public BookingVO bookTable(Long userId, BookingVO bookingVO) {
		Tables table = tableRepository.findByTableName(bookingVO.getTableName()).orElseThrow(
				() -> new EntityNotFoundException("Table not found with name: " + bookingVO.getTableName()));

		MealType mealType = mealTypeRepository.findByMealTypeName(bookingVO.getMealTypeName()).orElseThrow(
				() -> new EntityNotFoundException("Meal type not found with name: " + bookingVO.getMealTypeName()));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

		if (bookingVO.getNumberOfPersons() <= 0) {
			throw new IllegalArgumentException("Number of persons must be greater than 0.");
		}

		int availableSeats = getAvailableSeatsByTableName(bookingVO.getDate(), bookingVO.getMealTypeName(),
				bookingVO.getTableName());

		if (availableSeats < bookingVO.getNumberOfPersons()) {
			throw new IllegalStateException("Not enough available seats for booking.");
		}

		Booking newBooking = modelMapper.map(bookingVO, Booking.class);

		newBooking.setUser(user);
		newBooking.setTable(table);
		newBooking.setMealType(mealType);
		newBooking.setCanceled(false);

		return modelMapper.map(bookingRepository.save(newBooking), BookingVO.class);
	}

	//Check available seats by table name this function used for booking table
	
	public int getAvailableSeatsByTableName(LocalDate userDate, String mealTypeName, String tableName) {
		List<Object[]> result = bookingRepository.getAvailableSeatsByTableName(userDate, mealTypeName, tableName);

		return result.stream().filter(row -> tableName.equals(row[0])).findFirst()
				.map(row -> ((Number) row[1]).intValue())
				.orElseThrow(() -> new RuntimeException("Table not found: " + tableName));
	}

	// Check available seats with table name

	public List<TableAvailabilityVO> getAvailableSeats(BookingRequestVO bookingRequest) {
		LocalDate userDate = LocalDate.parse(bookingRequest.getDate());
		String mealTypeName = bookingRequest.getMealTypeName();

		List<Object[]> result = bookingRepository.getAvailableSeats(userDate, mealTypeName);

		return result.stream().map(row -> {
			String tableName = (String) row[0];
			int availableSeats = ((Number) row[2]).intValue();
			String isAvailable = availableSeats > 0 ? "Available" : "Not Available";
			return new TableAvailabilityVO(tableName, availableSeats, isAvailable);
		}).collect(Collectors.toList());
	}

}