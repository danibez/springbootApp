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
@RequestMapping("recifeirinha/api/clients")
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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginData) {
        try {
            Client authenticatedClient = service.authenticate(loginData.getEmail(), loginData.getPassword());
            // Caminho feliz: retorna 200 OK com os dados do cliente
            return ResponseEntity.ok(authenticatedClient);

        } catch (UserNotFoundException e) {
            // Cenário 1: Usuário não encontrado
            // Retorna 404 NOT FOUND com a mensagem de erro no corpo
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (RuntimeException e) {
            // Cenário 2: Senha incorreta
            // O código 401 Unauthorized é para credenciais inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/getById/{id}")
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