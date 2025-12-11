package com.example.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class EmployeeDto {

    private Long id;

    private String name;

    private  Integer age;

    @JsonProperty("isActive")
    private Boolean isActive;

    private LocalDate dateOfJoining;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, Integer age, Boolean isActive, LocalDate dateOfJoining) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.dateOfJoining = dateOfJoining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }
}
