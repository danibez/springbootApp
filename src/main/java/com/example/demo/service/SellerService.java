package com.example.demo.service;

import com.example.demo.exception.user.InvalidCredentialsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Seller;
import com.example.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService extends UserService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller create(Seller seller) {
        validateEmailUnique(seller.getEmail());
        validatePasswordForteil(seller.getPassword());
        validatePhone(seller.getPhone());
        validateCnpj(seller.getCnpj());
        seller.setEmail(normalizeEmail(seller.getEmail()));
        seller.setName(capitalizeName(seller.getName()));
        return sellerRepository.save(seller);
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Vendedor não encontrado com id: " + id));
    }

    public Seller update(Long id, Seller sellerDetails) {
        Seller existingSeller = findById(id); // Garante que o vendedor existe

        // Atualiza os campos do vendedor existente com os novos detalhes
        existingSeller.setName(capitalizeName(sellerDetails.getName()));
        existingSeller.setEmail(normalizeEmail(sellerDetails.getEmail()));
        existingSeller.setPassword(sellerDetails.getPassword()); // Considere re-validar a força da senha
        existingSeller.setStoreName(sellerDetails.getStoreName());
        existingSeller.setCnpj(sellerDetails.getCnpj()); // Considere re-validar o CNPJ

        return sellerRepository.save(existingSeller);
    }

    public void delete(Long id) {
        findById(id);
        sellerRepository.deleteById(id);
    }

    // MÉTODO DE AUTENTICAÇÃO ADICIONADO (idêntico ao de ClientService)
    public Seller authenticate(String email, String plainTextPassword) {
        String normalizedEmail = normalizeEmail(email);
        Seller seller = sellerRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new UserNotFoundException("Vendedor com email '" + email + "' não encontrado."));
        if (plainTextPassword.equals(seller.getPassword())) {
            return seller;
        } else {
            throw new InvalidCredentialsException("A senha fornecida está incorreta.");
        }
    }
}