package com.springboot.restaurant.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant.entity.Booking;
import com.springboot.restaurant.repository.BookingRepository;
import com.springboot.restaurant.vo.BookingVO;

@Service
public class UserService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ModelMapper modelMapper;

	 public BookingVO listBooking(BookingVO bookingVO) {
	        modelMapper.typeMap(BookingVO.class, Booking.class)
	                .addMappings(mapper -> {
	                    mapper.skip(Booking::setId);  // Exclude the conflicting property
	                    mapper.map(src -> src.getUserId().getUserId(), Booking::setUser);
	                    mapper.map(src -> src.getTableId().getTableId(), Booking::setTable);
	                    mapper.map(src -> src.getMealTypeId().getMealTypeId(), Booking::setMealType);
	                });

	        Booking booking = modelMapper.map(bookingVO, Booking.class);
	        return modelMapper.map(booking, BookingVO.class);
	    }
}
