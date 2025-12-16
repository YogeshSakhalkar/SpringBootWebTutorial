package com.example.springbootwebtutorial.repository;

import com.example.springbootwebtutorial.entites.DepartmentEntity;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

}
