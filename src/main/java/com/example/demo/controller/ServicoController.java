package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PetModel;
import com.example.demo.model.ServicoModel;
import com.example.demo.service.ServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @PutMapping("/{servicoId}/addPet/{petId}")
    public ServicoModel addPetToServico(
            @PathVariable Long servicoId,
            @PathVariable Long petId) {

        return servicoService.addPetToServico(servicoId, petId);
    }
    
    @PostMapping("/addServico")
    public ServicoModel newServico(@RequestBody ServicoModel serv) {
        System.out.println(serv.getNome());
        return servicoService.newServico(serv);
    }
    
}
