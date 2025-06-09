package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para receber as credenciais de login (email e senha).
 * Esta é a versão tradicional usando 'class', compatível com todas as versões do Java.
 */
@Getter
@AllArgsConstructor

public class LoginDTO {
    // 1. Campos (fields) privados para encapsular os dados
    private final String email;
    private final String password;

    // Setters não são incluídos para manter o objeto imutável (mais seguro).
}