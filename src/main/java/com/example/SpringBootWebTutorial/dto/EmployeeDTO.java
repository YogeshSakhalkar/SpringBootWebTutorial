package com.example.SpringBootWebTutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, Boolean isActive, LocalDate dataOfJoining, Integer age, String email, String name) {
        this.id = id;
        this.isActive = isActive;
        this.dateOfJoining = dataOfJoining;
        this.age = age;
        this.email = email;
        this.name = name;
    }
}
