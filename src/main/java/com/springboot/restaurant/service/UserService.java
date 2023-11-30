package com.springboot.restaurant.service;

import java.util.List;
import java.util.Map;
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
import com.springboot.restaurant.vo.AvailabilityRequestVO;
import com.springboot.restaurant.vo.AvailabilityResponseVO;
import com.springboot.restaurant.vo.BookingVO;

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

	public Map<String, Integer> checkAvailability(Long userId, AvailabilityRequestVO request) {
		BookingVO mealType=bookingRepository.findByMealTypeName(request.getMealTypeName()).orElseThrow(()->new RuntimeException("MealType not found"));
		BookingVO datecheck=bookingRepository.findByDate(request.getDate()).orElseThrow(()-> new RuntimeException("Date not found"));
		AvailabilityResponseVO availabilityResponseVO=modelMapper.map(request, AvailabilityResponseVO.class);
		availabilityResponseVO.setTableName(datecheck.getTableName());
		availabilityResponseVO.setAvailableSeats();
		
		return null;
	}
	 
}
