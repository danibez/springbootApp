package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PetModel;
import com.example.demo.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/pets")
public class PetController {
    @Autowired
    PetService petService;

    @GetMapping("/getPets")
    public List<PetModel> getAllPets() {
        return petService.getAllPets();
    }
    
    @GetMapping("/getPet/{id}")
    public PetModel getPetById(@PathVariable long id) {
        return petService.getPetById(id);
    }

    @PostMapping("/addPet")
    public PetModel addNewPet(@RequestBody PetModel pet) {
        return petService.addNewPet(pet);
    }
    
    
    
    
}
