package com.springboot.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant.service.AdminService;
import com.springboot.restaurant.vo.MealTypeVO;
import com.springboot.restaurant.vo.TableVO;

@RestController
@RequestMapping(path="/admin", produces= {"application/json"}, consumes= {"application/json"})
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/table/create")
    public ResponseEntity<TableVO> addTable(@RequestBody TableVO tableVO) {
        TableVO createdTableVO = adminService.addTable(tableVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTableVO);
    }

    @PutMapping("/table/update/{tableId}")
    public ResponseEntity<TableVO> updateTable(@RequestBody TableVO tableVO, @PathVariable Long tableId) {
        TableVO updateTableVO = adminService.updateTable(tableVO, tableId);
        return ResponseEntity.status(HttpStatus.OK).body(updateTableVO);
    }

    @DeleteMapping("/table/delete/{tableId}")
    public String deleteTable(@PathVariable Long tableId) {
    	 TableVO tableVO = new TableVO();
    	    tableVO.setTableId(tableId);
    	    adminService.deleteTable(tableVO);
    	    return "Deleted successfully";
	}
    @PostMapping("/meal/create")
    public ResponseEntity<MealTypeVO> addMeal(@RequestBody MealTypeVO mealTypeVO){
    	MealTypeVO CreateMealTypeVO= adminService.addMeal(mealTypeVO);
    	return ResponseEntity.status(HttpStatus.CREATED).body(CreateMealTypeVO);

    }
}