package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Seller;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    @Autowired
    private SellerService service;

    // Endpoint de criação padronizado para retornar 201 Created com o corpo
    @PostMapping("/create")
    public ResponseEntity<Seller> create(@RequestBody Seller seller) {
        Seller created = service.create(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ROTA DE LOGIN ADICIONADA COM TRATAMENTO DE ERROS
    @PostMapping("/login")
    public ResponseEntity<Seller> login(@RequestBody LoginDTO loginData) {
        Seller authenticatedSeller = service.authenticate(loginData.getEmail(), loginData.getPassword());
        return ResponseEntity.ok(authenticatedSeller);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Seller>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Seller> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Seller> update(@PathVariable Long id, @RequestBody Seller seller) {
        return ResponseEntity.ok(service.update(id, seller));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}