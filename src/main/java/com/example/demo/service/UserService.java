package com.example.demo.service;

import com.example.demo.exception.client.InvalidCpfException;
import com.example.demo.exception.seller.InvalidCnpjException;
import com.example.demo.exception.user.EmailAlreadyExistsException;
import com.example.demo.exception.user.InvalidPasswordException;
import com.example.demo.exception.user.InvalidPhoneException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UserService {
    @Autowired
    private UserRepository userRepository;

    protected void validateEmailUnique(String email) {
        if (userRepository.existsByEmail(email.toLowerCase())){
            throw new EmailAlreadyExistsException("Email já existe");
        }
    }

    protected String normalizeEmail (String email) {
        return email.trim().toLowerCase();
    }

    protected void validatePasswordForteil (String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&]).{8,}$")) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 8 caracteres, com letras maiúsculas, minúsculas, números e símbolos.");
        }
    }

    protected void validatePhone (String phone) {
        if (!phone.matches("^\\(\\d{2}\\)\\s?\\d{4,5}-?\\d{4}$")) {
            throw new InvalidPhoneException("Telefone inválido. Ex: (81) 99999-1234");
        }
    }

    protected void validateCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new InvalidCpfException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
    }

    protected void validarCnpj(String cnpj) {
        if (!cnpj.matches("\\d{14}")) {
            throw new InvalidCnpjException("CNPJ inválido. Deve conter 14 dígitos numéricos.");
        }
    }

    protected String capitalizeName(String name) {
        String[] parts = name.trim().toLowerCase().split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String parte : parts) {
            if (!parte.isEmpty()) {
                capitalized.append(Character.toUpperCase(parte.charAt(0))).append(parte.substring(1)).append(" ");
            }
        }
        return capitalized.toString().trim();
    }


}
