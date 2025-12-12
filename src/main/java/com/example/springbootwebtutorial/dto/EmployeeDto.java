package com.example.springbootwebtutorial.dto;

import com.example.springbootwebtutorial.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotNull(message = "Requied field in employee name")
    @Size( min = 3,max = 10,message = "Number of character should me in the range of [3 to 10]")
    private String name;

    @NotNull(message = "Age cannot be empty")
    @Max(value = 80,message = "Age of employee cannot be greter than 80")
    @Min(value = 18,message = "Age of Employee cannot be less than 18")
    private  Integer age;

    @Email(message = "Email should be valid email")
    private String email;

    //@JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;

    @PastOrPresent(message = "Date of joining field cannot be in the future")
    private LocalDate dateOfJoining;

    //  @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of an employee can be admin or user")
    @NotBlank(message = "Role of employee cannot be blank")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary of employee should not be null")
    @Positive(message = "salary of the employee should be in positive integer")
    @Digits(integer =  6, fraction = 2, message = "The salary can be form of xxx.yy")
    @DecimalMin(value = "100.50")
    @DecimalMax(value = "100000.99")
    private Double salary;

}
