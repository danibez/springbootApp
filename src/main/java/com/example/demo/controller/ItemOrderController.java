package com.example.demo.controller;

import com.example.demo.model.ItemOrder;
import com.example.demo.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemOrderController {

    @Autowired
    private ItemOrderService service;

    @PostMapping
    public ResponseEntity<ItemOrder> create(@RequestBody ItemOrder itemOrder) {
        ItemOrder created = service.create(itemOrder);
        return ResponseEntity.created(URI.create("/items/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ItemOrder>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrder> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemOrder> update(@PathVariable Long id, @RequestBody ItemOrder itemOrder) {
        return ResponseEntity.ok(service.update(id, itemOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
