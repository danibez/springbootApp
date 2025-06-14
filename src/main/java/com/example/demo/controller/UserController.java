package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Lista de Métodos - Classe de Serviço
// MÉTODO - RETORNO - DESCRIÇÃO
// getAllUsers() - List<UserModel> - Retorna todos os usuários
// getUserById(Long id) - UserModel - Busca um usuário pelo ID
// createUser(UserModel client) - UserModel - Cria um novo usuário
// updateUser(Long id, UserModel product) - UserModel ou null - Atualiza um usuário existente
// deleteUser(Long id) - void - Deleta um usuário pelo ID

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String helloString() {
        return "Hello World";
    }
}
