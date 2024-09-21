package com.example.SpringBootWebTutorial.controller;

import com.example.SpringBootWebTutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @GetMapping(path = "/getSecreateMessage")
    public String getSecreateMessage(){
        return "Secreat Message : addsa#@DSA";
    }

    @GetMapping(path = "/employee/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
        return new EmployeeDTO(1L,true, LocalDate.of(2024, 7, 1),27,
                "yogeshsakhalkar70@gamil.com","Yogesh");
    }

    //@GetMapping(path = "/employees")
    @GetMapping
    public String getAllEmployees(@RequestParam (required = false, name = "inputAge") Integer age,
                                  @RequestParam String sortBy){
        return "Hi age "+age+" "+sortBy;
    }
}
