package com.example.demo.controller;

import com.example.demo.model.ItemOrder;
import com.example.demo.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemOrderController {

    @Autowired
    private ItemOrderService service;

    @PostMapping("/create")
    public ResponseEntity<ItemOrder> create(@RequestBody ItemOrder itemOrder) {
        ItemOrder created = service.create(itemOrder);
        return ResponseEntity.created(URI.create("/items/" + created.getId())).body(created);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ItemOrder>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<ItemOrder> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemOrder> update(@PathVariable Long id, @RequestBody ItemOrder itemOrder) {
        return ResponseEntity.ok(service.update(id, itemOrder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
