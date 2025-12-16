package com.example.springbootwebtutorial.service;

import com.example.springbootwebtutorial.advices.ResourceNotFoundException;
import com.example.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.entites.DepartmentEntity;
import com.example.springbootwebtutorial.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public List<DepartmentDto> getAllDepartment(){
        List<DepartmentEntity> departmentDtoList = departmentRepository.findAll();
        return departmentDtoList
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto saveDepartmentDetails(DepartmentDto departmentDto){
        DepartmentEntity toSavedepartmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        System.out.println(toSavedepartmentEntity);
        DepartmentEntity departmentEntity = departmentRepository.save(toSavedepartmentEntity);
        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    public Boolean deleteDepartmentDetails(Long departmentId){
        Boolean ifExsist = ifExsitByDepartmentId(departmentId);
        if(ifExsist){
            departmentRepository.deleteById(departmentId);
            return true;
        }else{
            return false;
        }
    }

    public DepartmentDto updateDepartmentDetailsById(Long departmentId, DepartmentDto departmentDto){
        Boolean ifExsist = ifExsitByDepartmentId(departmentId);
        if(!ifExsist) throw new ResourceNotFoundException("Department Not found "+departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity saveDepartmentData = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDepartmentData, DepartmentDto.class);
    }


    public Boolean ifExsitByDepartmentId(Long departmentId){
        return departmentRepository.existsById(departmentId);

    }
}
