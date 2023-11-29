package com.springboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
