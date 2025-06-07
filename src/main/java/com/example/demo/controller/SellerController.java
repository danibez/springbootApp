package com.example.demo.controller;

import com.example.demo.model.Seller;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("reifeirinha/sellers")
public class SellerController {
    @Autowired
    private SellerService service;

    @PostMapping
    public ResponseEntity<Seller> create(@RequestBody Seller seller) {
        Seller created = service.creat(seller);
        return ResponseEntity.created(URI.create("/sellers/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> update(@PathVariable Long id, @RequestBody Seller seller) {
        return ResponseEntity.ok(service.update(id, seller));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

