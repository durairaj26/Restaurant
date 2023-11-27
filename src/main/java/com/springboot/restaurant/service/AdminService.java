
package com.springboot.restaurant.service;

import com.springboot.restaurant.entity.Tables;
import com.springboot.restaurant.vo.TableVO;
import com.springboot.restaurant.repository.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    TableRepository tableRepository;
    @Autowired
    ModelMapper modelMapper;

    public TableVO addTable(TableVO tableVO) {
        Tables tables = this.convertTabletVOtoTable(tableVO);
        tableRepository.save(tables);
        return this.convertTabletoTableVO(tables);
    }

    public TableVO convertTabletoTableVO(Tables tables) {

        modelMapper.typeMap(Tables.class, TableVO.class).addMappings(mapper -> {
            mapper.map(Tables::getTableName, TableVO::setTableName);
            mapper.map(Tables::getSeatingCapacity, TableVO::setSeatingCapacity);
        });
        return modelMapper.map(tables, TableVO.class);
    }

    public Tables convertTabletVOtoTable(TableVO tableVO) {
        modelMapper.typeMap(TableVO.class, Tables.class).addMappings(mapper -> {
            mapper.map(TableVO::getTableName, Tables::setTableName);
            mapper.map(TableVO::getSeatingCapacity, Tables::setSeatingCapacity);
        });
        return modelMapper.map(tableVO, Tables.class);
    }
}
