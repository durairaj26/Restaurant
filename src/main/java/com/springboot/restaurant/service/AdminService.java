
package com.springboot.restaurant.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant.entity.MealType;
import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.repository.MealTypeRepository;
import com.springboot.restaurant.repository.TableRepository;
import com.springboot.restaurant.vo.MealTypeVO;
import com.springboot.restaurant.vo.TableVO;

@Service
public class AdminService {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    MealTypeRepository mealTypeRepository;

    @Autowired
    ModelMapper modelMapper;

    public TableVO addTable(TableVO tableVO) {
        Tables tables = modelMapper.map(tableVO, Tables.class);
        tableRepository.save(tables);
        return  modelMapper.map(tables, TableVO.class);
    }

	public TableVO updateTable(TableVO tableVO, Long tableId) {
		Tables updateTable = tableRepository.findById(tableId)
				.orElseThrow(() -> new RuntimeException("Table not found"));
		modelMapper.map(tableVO,updateTable);
		tableRepository.save(updateTable);
		return  modelMapper.map(updateTable, TableVO.class);
	}

	public void deleteTable(TableVO tableVO) {
		Tables tableToDelete = modelMapper.map(tableVO, Tables.class);
	    tableRepository.findById(tableToDelete.getTableId())
	                   .orElseThrow(() -> new RuntimeException("Table not found"));
	    tableRepository.deleteById(tableToDelete.getTableId());

	}

	public MealTypeVO addMeal(MealTypeVO mealTypeVO) {
		MealType mealType=modelMapper.map(mealTypeVO, MealType.class);
		mealTypeRepository.save(mealType);
		return modelMapper.map(mealType, MealTypeVO.class);
	}
}
