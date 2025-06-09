package com.example.demo.service;

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClientService extends UserService {

    @Autowired
    ClientRepository clientRepository;

    public Client createClient(@RequestBody Client client) { //falta validar email unico
        validateEmailUnique(client.getEmail());
        validatePasswordForteil(client.getPassword());
        client.setEmail(normalizeEmail(client.getEmail()));
        client.setName(capitalizeName(client.getName()));
        return clientRepository.save(client);
    }

    public List<Client> finAllClients() {
        return clientRepository.findAll();
    }

    public Client finClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Cliente não encontrado com id: " + id));
    }
    public Client update(Long id, Client client) {
        clientRepository.findById(id);
        client.setId(id);
        client.setEmail(normalizeEmail(client.getEmail()));
        client.setName(capitalizeName(client.getName()));
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.findById(id);
        clientRepository.deleteById(id);
    }

    // MÉTODO DE AUTENTICAÇÃO ATUALIZADO
    public Client authenticate(String email, String plainTextPassword) {
        // 1. Busca o cliente pelo email normalizado
        String normalizedEmail = normalizeEmail(email);
        Client client = clientRepository.findByEmail(normalizedEmail)
                // Lança uma exceção específica se o usuário não for encontrado
                .orElseThrow(() -> new UserNotFoundException("Usuário com email '" + email + "' não encontrado."));

        // 2. Se o usuário foi encontrado, verifica a senha
        if (plainTextPassword.equals(client.getPassword())) {
            return client; // Sucesso
        } else {
            // Se a senha estiver incorreta, lança outra exceção
            throw new RuntimeException("A senha fornecida está incorreta.");
        }
    }



}
