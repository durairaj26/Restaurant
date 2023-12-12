
package com.springboot.restaurant.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant.entity.MealType;
import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.repository.MealTypeRepository;
import com.springboot.restaurant.repository.TableRepository;
import com.springboot.restaurant.repository.UserRepository;
import com.springboot.restaurant.vo.MealTypeVO;
import com.springboot.restaurant.vo.TableVO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {

	@Autowired
	TableRepository tableRepository;

	@Autowired
	MealTypeRepository mealTypeRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

	// Create table

	public TableVO addTable(TableVO tableVO, Long userId) {
		userRepository.findById(userId).filter(user -> "ADMIN".equals(user.getRole()))
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with userId: " + userId));

		Tables tables = modelMapper.map(tableVO, Tables.class);
		tableRepository.save(tables);
		return modelMapper.map(tables, TableVO.class);
	}

	// Update table by table id

	public TableVO updateTable(TableVO tableVO, Long tableId, Long userId) {
		userRepository.findById(userId).filter(user -> "ADMIN".equals(user.getRole()))
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with userId: " + userId));

		tableVO.setTableId(tableId);
		tableRepository.findById(tableVO.getTableId()).orElseThrow(
				() -> new EntityNotFoundException("Table not found with table id: " + tableVO.getTableId()));
		Tables updateTable = modelMapper.map(tableVO, Tables.class);
		tableRepository.save(updateTable);
		return modelMapper.map(updateTable, TableVO.class);
	}

	// Delete table by table id

	public void deleteTable(Long tableId, Long userId) {
		userRepository.findById(userId).filter(user -> "ADMIN".equals(user.getRole()))
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with userId: " + userId));

		tableRepository.findById(tableId)
				.orElseThrow(() -> new EntityNotFoundException("Table not found with table id: " + tableId));
		tableRepository.deleteById(tableId);

	}

	// Create mealType

	public MealTypeVO addMeal(MealTypeVO mealTypeVO, Long userId) {
		userRepository.findById(userId).filter(user -> "ADMIN".equals(user.getRole()))
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with userId: " + userId));

		MealType mealType = modelMapper.map(mealTypeVO, MealType.class);
		mealTypeRepository.save(mealType);
		return modelMapper.map(mealType, MealTypeVO.class);
	}
}
