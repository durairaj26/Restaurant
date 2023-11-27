package com.springboot.restaurant.controller;

import com.springboot.restaurant.vo.TableVO;
import com.springboot.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin", produces= {"application/json"}, consumes= {"application/json"})
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<TableVO> addTable(@RequestBody TableVO tableVO) {
        TableVO createdTableVO = adminService.addTable(tableVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTableVO);
    }
}