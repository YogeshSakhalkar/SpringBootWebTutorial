package com.example.SpringBootWebTutorial.controller;

import com.example.SpringBootWebTutorial.dto.EmployeeDTO;
import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import com.example.SpringBootWebTutorial.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeDataController {

    private final EmployeeService employeeService;

    public EmployeeDataController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeid}")
    public ResponseEntity<EmployeeDTO> getEmployeeBYId(@PathVariable (name = "employeeid")Long id){
        //EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //ModelMapper modelMapper = new ModelMapper();
        //return employeeRepository.findById(id).orElse(null);
        //return modelMapper.map(employeeEntity, EmployeeDTO.class);

        /* without using optional */
        //return employeeService.getEmployeeById(id);

        /*with using optional*/
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        System.out.println("inside controller");
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeeEntity){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(employeeEntity);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,
                                          @PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.updateEmployeeBy(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted =employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
