package com.example.springbootwebtutorial.controller;

import com.example.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.entites.EmployeeEntity;
import com.example.springbootwebtutorial.repository.EmployeeRepository;
import com.example.springbootwebtutorial.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


//    @GetMapping(path = "/message")
//    public String getMySupportSecretMessge(){
//        return "i will be pro in DSA";
//    }


    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long id){
//        return new EmployeeDto(employeeId, "Yogesh",29,true, LocalDate.now());
//            return employeeService.findById(id);
        Optional<EmployeeDto> employeeDto = Optional.ofNullable(employeeService.findById(id));
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployeeData(@RequestBody @Valid EmployeeDto employeedata){
 //       return employeeService.saveEmployeeData(employeedata);
        EmployeeDto savedEmployee = employeeService.saveEmployeeData(employeedata);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeData(){
 //       return employeeService.findAll();
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping
    public Boolean deleteEmployeeById(Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
 //       return employeeService.updatePartialEmployeeById(employeeId, updates);
        EmployeeDto employeeDto = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeDataById(@RequestBody @Valid EmployeeDto employeeEntity,
                                              @PathVariable Long employeeId){
 //       return employeeService.updateEmployeeById(employeeId, employeeEntity);
        return ResponseEntity.ok((employeeService.updateEmployeeById(employeeId, employeeEntity)));
    }
}
