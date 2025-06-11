package com.example.demo.controller;


import com.example.demo.dto.LoginDTO;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client created = service.create(client);
        return ResponseEntity.status(201).body(created);
    }

    // ROTA DE LOGIN ATUALIZADA COM TRATAMENTO DE ERROS
    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody LoginDTO loginData) {
        Client authenticatedClient = service.authenticate(loginData.getEmail(), loginData.getPassword());
        return ResponseEntity.ok(authenticatedClient);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        Client updated = service.update(id, client);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}