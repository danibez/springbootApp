package com.example.demo.service;

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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(Order order) {
        // 1. Validar se o cliente existe
        Client client = clientRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new UserNotFoundException("Cliente com id " + order.getClient().getId() + " não encontrado."));
        order.setClient(client);
        order.setDate(LocalDateTime.now());

        // 2. Validar cada produto do pedido e configurar o relacionamento
        for (ItemOrder item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Produto com id " + item.getProduct().getId() + " não encontrado."));
            item.setProduct(product);
            item.setPrice(product.getPrice()); // Garante que o preço é o atual do produto
            item.setOrder(order); // Estabelece a relação bidirecional
        }

        // 3. Configurar relacionamento com o pagamento, se houver
        if (order.getPayment() != null) {
            order.getPayment().setOrder(order);
        }

        return orderRepository.save(order);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido com id " + id + " não encontrado."));
    }

    @Transactional
    public void deleteOrderById(Long id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }

    // ATENÇÃO: A lógica de 'update' de um pedido pode ser muito complexa.
    // Esta implementação atualiza apenas o status. Alterar itens ou cliente
    // de um pedido já feito geralmente não é uma boa prática.
    @Transactional
    public Order updateOrderStatus(Long id, Order orderUpdateDetails) {
        Order existingOrder = findOrderById(id);

        // Apenas o status do pedido pode ser atualizado por este método.
        if (orderUpdateDetails.getStatus() != null) {
            existingOrder.setStatus(orderUpdateDetails.getStatus());
        }

        return orderRepository.save(existingOrder);
    }
}
