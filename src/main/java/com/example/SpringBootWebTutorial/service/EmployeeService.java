package com.example.SpringBootWebTutorial.service;

import com.example.SpringBootWebTutorial.dto.EmployeeDTO;
import com.example.SpringBootWebTutorial.entity.EmployeeEntity;
import com.example.SpringBootWebTutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(ModelMapper modelMapper,EmployeeRepository employeeRepository){
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    //without using optional
    /*
    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

     */

    //with using optional
    public Optional<EmployeeDTO> getEmployeeById(Long id){
        return employeeRepository.findById(id).map(employeeEntity ->
                modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        System.out.println(employeeEntityList);
        return employeeEntityList.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);

        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    /**
     * In this Method logic if new employeeid is inserted then it will be set as new record
     * */
    public EmployeeDTO updateEmployeeBy(Long employeeId, EmployeeDTO employeeDto){
        EmployeeEntity employeeEntity =modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);

    }

    public boolean deleteEmployeeById(Long employeeId){
        boolean exist = isExistsByEmployeeId(employeeId);
        if(exist){

            employeeRepository.deleteById(employeeId);
            return true;
        }else{

            System.out.println("EmployeeId not found");
        }
        return false;
    }

    public boolean isExistsByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    /**
     * In this API we have use reflection utils
     * */
    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates){
        boolean exists = isExistsByEmployeeId(employeeId);
        if(!exists)
            return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        //get all the field by its name
        updates.forEach((field,value)->{

            Field fieldToBeUpdated =ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);//make it accessible
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);//update it
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);//saved it in DB
    }
}
