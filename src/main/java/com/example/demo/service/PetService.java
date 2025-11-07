package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PetModel;
import com.example.demo.model.ServicoModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.ServicoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ServicoRepository servicoRepository;

    public List<PetModel> getAllPets() {
        return petRepository.findAll();
    }

    public PetModel getPetById(long id) {
        return petRepository.findById(id).get();
    }

    public List<PetModel> getPetByDono(long id){
        UserModel dono = userRepository.findById(id).get();
        return petRepository.findByDono(dono);
    }

    public PetModel addNewPet(PetModel pet) {
        UserModel dono = userRepository.findById(pet.getDono().getId())
                                        .orElseThrow(()->new RuntimeException("Usuário não encontrado!"));
        pet.setDono(dono);
        return petRepository.save(pet);
    }
    
}
