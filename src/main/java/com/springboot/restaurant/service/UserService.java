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

	public List<BookingVO> listBooking(Long userId) {
		return bookingRepository.findByUserUserId(userId).stream()
				.map(booking -> modelMapper.map(booking, BookingVO.class)).collect(Collectors.toList());
	}

	public BookingVO bookTable(Long userId, BookingVO bookingVO) {
		Tables table = tableRepository.findByTableName(bookingVO.getTableName()).orElseThrow(
				() -> new EntityNotFoundException("Table not found with name: " + bookingVO.getTableName()));
		MealType mealType = mealTypeRepository.findByMealTypeName(bookingVO.getMealTypeName()).orElseThrow(
				() -> new EntityNotFoundException("Meal type not found with name: " + bookingVO.getMealTypeName()));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

		Booking newBooking = modelMapper.map(bookingVO, Booking.class);
		newBooking.setUser(user);
		newBooking.setTable(table);
		newBooking.setMealType(mealType);
		newBooking.setCanceled(false);

		Booking savedBooking = bookingRepository.save(newBooking);
		return modelMapper.map(savedBooking, BookingVO.class);
	}

	public List<TableAvailabilityVO> getAvailableSeats(BookingRequestVO bookingRequest) {
		LocalDate userDate = LocalDate.parse(bookingRequest.getDate());
		String mealTypeName = bookingRequest.getMealTypeName();

		List<Object[]> result = bookingRepository.getAvailableSeats(userDate.atStartOfDay(), mealTypeName);

		List<TableAvailabilityVO> availableSeats = result.stream().map(row -> new TableAvailabilityVO((String) row[0],
				((Number) row[1]).intValue(), ((Number) row[2]).intValue())).collect(Collectors.toList());

		return availableSeats;
	}

}