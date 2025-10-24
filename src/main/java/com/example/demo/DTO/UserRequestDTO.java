package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDTO {
    
    @NotBlank
    @NotEmpty
    String username;

    @Size(min = 8)
    String password;

    int age;
}
