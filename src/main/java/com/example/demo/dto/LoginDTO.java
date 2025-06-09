package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) para receber as credenciais de login (email e senha).
 * Esta é a versão tradicional usando 'class', compatível com todas as versões do Java.
 */
public class LoginDTO {

    // 1. Campos (fields) privados para encapsular os dados
    private final String email;
    private final String password;

    // 2. Construtor público para inicializar os campos
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 3. Métodos "getters" públicos para permitir o acesso aos campos
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters não são incluídos para manter o objeto imutável (mais seguro).
    // Métodos equals(), hashCode() e toString() são omitidos por simplicidade,
    // mas podem ser gerados automaticamente pela sua IDE se necessário.
}