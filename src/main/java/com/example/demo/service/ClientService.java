package com.example.demo.service;

import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends UserService {

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client) {
        validateEmailUnique(client.getEmail());
        validatePasswordForteil(client.getPassword());
        validatePhone(client.getPhone());
        validateCpf(client.getCpf());
        client.setEmail(normalizeEmail(client.getEmail()));
        client.setName(capitalizeName(client.getName()));
        return clientRepository.save(client);
    }

    public List<Client> finAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Cliente não encontrado com id: " + id));
    }
    public Client update(Long id, Client client) {
        findById(id);
        client.setEmail(normalizeEmail(client.getEmail()));
        client.setName(capitalizeName(client.getName()));
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        findById(id);
        clientRepository.deleteById(id);
    }
}
