package com.example.demo.service;

import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.exception.order.EmptyOrderException;
import com.example.demo.exception.order.OrderNotFoundException;
import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.model.ItemOrder;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository repository;
    @Autowired private ClientRepository clientRepository;

    public Order create(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new EmptyOrderException("Pedido deve conter pelo menos um item.");
        }

        Client client = clientRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado."));

        order.setClient(client);
        return repository.save(order);
    }

    public List<Order> findAll() { return repository.findAll(); }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado."));
    }

    public Order update(Long id, Order order) {
        findById(id);
        order.setId(id);
        return repository.save(order);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}