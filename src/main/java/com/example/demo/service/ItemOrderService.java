package com.example.demo.service;

import com.example.demo.exception.item.InvalidItemQuantityException;
import com.example.demo.exception.item.ItemOrderNotFoundException;
import com.example.demo.exception.order.OrderNotFoundException;
import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.model.ItemOrder;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ItemOrderRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOrderService {

    @Autowired
    private ItemOrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public ItemOrder create(ItemOrder itemOrder) {
        if (itemOrder.getQuantity() <= 0) {
            throw new InvalidItemQuantityException("Quantidade do item deve ser maior que zero.");
        }

        Product product = productRepository.findById(itemOrder.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));

        Order order = orderRepository.findById(itemOrder.getOrder().getId())
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado."));

        itemOrder.setProduct(product);
        itemOrder.setOrder(order);
        return repository.save(itemOrder);
    }

    public List<ItemOrder> findAll() { return repository.findAll(); }

    public ItemOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemOrderNotFoundException("Item não encontrado."));
    }

    public ItemOrder update(Long id, ItemOrder itemOrder) {
        findById(id);
        itemOrder.setId(id);
        return repository.save(itemOrder);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

