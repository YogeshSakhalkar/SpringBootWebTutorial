package com.example.SpringBootWebTutorial.service;

import com.example.SpringBootWebTutorial.dto.EmployeeDTO;
import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import com.example.SpringBootWebTutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        return employeeEntityList.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);

        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}
