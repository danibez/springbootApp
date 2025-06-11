package com.example.demo.service;

import com.example.demo.exception.user.InvalidCredentialsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
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

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Cliente não encontrado com id: " + id));
    }

    public Client update(Long id, Client clientDetails) {
        Client existingClient = findById(id);

        // Valida e atualiza o email (se for diferente do atual)
        if (clientDetails.getEmail() != null && !Objects.equals(normalizeEmail(clientDetails.getEmail()), existingClient.getEmail())) {
            String newEmail = normalizeEmail(clientDetails.getEmail());
            validateEmailUnique(newEmail);
            existingClient.setEmail(newEmail);
        }

        // Valida e atualiza os outros campos
        if (clientDetails.getName() != null) {
            existingClient.setName(capitalizeName(clientDetails.getName()));
        }
        if (clientDetails.getPassword() != null) {
            validatePasswordForteil(clientDetails.getPassword());
            existingClient.setPassword(clientDetails.getPassword());
        }
        if (clientDetails.getPhone() != null) {
            validatePhone(clientDetails.getPhone());
            existingClient.setPhone(clientDetails.getPhone());
        }
        if (clientDetails.getDeliveryAddress() != null) {
            existingClient.setDeliveryAddress(clientDetails.getDeliveryAddress());
        }
        return clientRepository.save(existingClient);
    }

    public void delete(Long id) {
        findById(id);
        clientRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Client authenticate(String email, String plainTextPassword) {
        String normalizedEmail = normalizeEmail(email);
        Client client = clientRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuário com email '" + email + "' não encontrado."));
        if (plainTextPassword.equals(client.getPassword())) {
            return client;
        } else {
            throw new InvalidCredentialsException("A senha fornecida está incorreta.");
        }
    }
}