package com.example.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotNull(message = "This field requied title")
    @Size(min = 2, max = 20, message = "Character should be in the range of 2 to 20")
    private String title;

    @AssertTrue(message = "isActive should be true")
    private Boolean active;


    @FutureOrPresent(message = "created date should be of future or present")
    private LocalDate createdAt;
}
