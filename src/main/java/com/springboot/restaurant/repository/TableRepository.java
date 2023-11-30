package com.springboot.restaurant.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.restaurant.entity.Tables;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {
    Optional<Tables> findByTableName(String tableName);

}