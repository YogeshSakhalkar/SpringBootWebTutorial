package com.example.SpringBootWebTutorial.repository;

import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<EmployeeEntity,Long> {
    List<EmployeeEntity> findByName(String name);
}
