package com.example.springbootwebtutorial.service;

import com.example.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.entites.EmployeeEntity;
import com.example.springbootwebtutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDto findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    public EmployeeDto saveEmployeeData(EmployeeEntity employeeData) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeData, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> findAll() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
         return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public Boolean deleteEmployeeById(Long employeeId){
        Boolean exist = isExistByEmployeeId(employeeId);
        if(exist){
            employeeRepository.deleteById(employeeId);
            return true;
        }else{
            return false;
        }
    }

    public Boolean isExistByEmployeeId(Long employeeId){
        return  employeeRepository.existsById(employeeId);
    }

    public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates){
        boolean exist = isExistByEmployeeId(employeeId);
        if(!exist)  return null;
            EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
            updates.forEach((field,value)->{
            Field filedToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            filedToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(filedToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }
}
