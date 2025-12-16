package com.example.springbootwebtutorial.controller;

import com.example.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.service.DepartmentService;
import org.apache.el.parser.BooleanNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllEmployeeDetails(){
        List<DepartmentDto> listOFDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(listOFDepartment);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartmentDetails(@RequestBody DepartmentDto departmentDto){
        DepartmentDto dataSaved = departmentService.saveDepartmentDetails(departmentDto);
        return new ResponseEntity<>(dataSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long departmentId){
        Boolean departmentDeleted = departmentService.deleteDepartmentDetails(departmentId);
        if(departmentDeleted){
            return  ResponseEntity.status(HttpStatus.OK).body("Department deleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department id not found");
        }
    }


    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> updateEmployeeDetailsById(@RequestBody DepartmentDto departmentDto,
                                                   @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentDetailsById(departmentId, departmentDto));
    }


}
