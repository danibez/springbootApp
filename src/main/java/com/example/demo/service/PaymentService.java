package com.example.demo.service;

import com.example.demo.exception.order.OrderAlreadyPaidException;
import com.example.demo.exception.order.OrderNotFoundException;
import com.example.demo.exception.payment.InsufficientPaymentException;
import com.example.demo.exception.payment.PaymentNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.model.enums.*;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private OrderRepository orderRepository;

    public Payment create(Payment payment) {
        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado."));

        if (order.getStatus() == OrderStatus.PAGO) {
            throw new OrderAlreadyPaidException("O pedido já foi pago anteriormente.");
        }

//        if (payment.getAmount().doubleValue() < order.getTotal().doubleValue()) {
//            throw new InsufficientPaymentException("O valor do pagamento é inferior ao total do pedido.");
//        }

        payment.setOrder(order);
        return repository.save(payment);
    }

    public List<Payment> findAll() { return repository.findAll(); }

    public Payment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Pagamento não encontrado."));
    }

    public Payment update(Long id, Payment payment) {
        findById(id);
        payment.setId(id);
        return repository.save(payment);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
