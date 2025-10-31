package com.example.demo.DTO;

import java.util.List;

import com.example.demo.model.PetModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    long id;
    String username;
    int age;

    @OneToMany(mappedBy = "dono")
    @JsonManagedReference
    List<PetModel> pets;
}
