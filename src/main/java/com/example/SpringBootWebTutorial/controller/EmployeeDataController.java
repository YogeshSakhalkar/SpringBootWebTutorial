package com.example.SpringBootWebTutorial.controller;

import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import com.example.SpringBootWebTutorial.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeDataController {

    private final EmployeeRepository employeeRepository;

    public EmployeeDataController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeid}")
    public EmployeeEntity getEmployeeBYId(@PathVariable (name = "employeeid")Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

}
