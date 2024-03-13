package com.colak.springreactivejakartavalidationtutorial.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Min(value = 10, message = "Required min age is 10")
    @Max(value = 50, message = "Required max age is 50")
    private int age;
}
