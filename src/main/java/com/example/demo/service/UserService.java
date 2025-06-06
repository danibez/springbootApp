package com.example.demo.service;

import com.example.demo.exception.user.EmailAlreadyExistsException;
import com.example.demo.exception.user.InvalidPasswordException;
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
