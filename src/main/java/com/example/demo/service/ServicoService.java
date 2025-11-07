package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.controller.ServicoController;
import com.example.demo.model.PetModel;
import com.example.demo.model.ServicoModel;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    PetRepository petRepository;

    public ServicoModel addPetToServico(
            @PathVariable Long servicoId,
            @PathVariable Long petId) {

        ServicoModel servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        PetModel pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        // adiciona o pet à lista de pets do serviço
        servico.getPets().add(pet);

        ServicoModel updated = servicoRepository.save(servico);
        return updated;
    }

    public ServicoModel newServico(ServicoModel servico) {
        System.out.println(servico.getNome());
        return servicoRepository.save(servico);
    }
}
