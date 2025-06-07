package com.example.demo.service;

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Seller;
import com.example.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService extends UserService {

    @Autowired
    private SellerRepository repository;

    public Seller creat (Seller seller) {
        validateEmailUnique(seller.getEmail());
        validatePasswordForteil(seller.getPassword());
        validarCnpj(seller.getCnpj());
        seller.setEmail(normalizeEmail(seller.getEmail()));
        seller.setName(capitalizeName(seller.getName()));
        return repository.save(seller);
    }

    public List<Seller> findAll() {
        return repository.findAll();
    }

    public Seller findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Vendedor não encontrado com id: " + id));
    }

    public Seller update (Long id, Seller seller) {
        findById(id);
        seller.setEmail(normalizeEmail(seller.getEmail()));
        seller.setName(capitalizeName(seller.getName()));
        return repository.save(seller);
    }

    public void delete (Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
