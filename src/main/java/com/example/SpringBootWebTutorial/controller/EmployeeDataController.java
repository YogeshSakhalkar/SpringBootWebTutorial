package com.example.SpringBootWebTutorial.controller;

import com.example.SpringBootWebTutorial.dto.EmployeeDTO;
import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import com.example.SpringBootWebTutorial.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeDataController {

    private final EmployeeService employeeService;

    public EmployeeDataController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeid}")
    public EmployeeDTO getEmployeeBYId(@PathVariable (name = "employeeid")Long id){
        //EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //ModelMapper modelMapper = new ModelMapper();
        //return employeeRepository.findById(id).orElse(null);
        //return modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }


    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeEntity){
        return employeeService.createNewEmployee(employeeEntity);
    }

}
