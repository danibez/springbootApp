package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/creat")
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        Payment created = service.create(payment);
        return ResponseEntity.created(URI.create("/payments/" + created.getId())).body(created);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment payment) {
        return ResponseEntity.ok(service.update(id, payment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
