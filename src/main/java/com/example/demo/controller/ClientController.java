package com.example.demo.controller;


import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recifeirinha/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client created = service.createClient(client);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.finAllClients());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.finClientById(id));
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
