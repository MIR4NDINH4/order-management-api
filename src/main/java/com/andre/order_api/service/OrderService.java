package com.andre.order_api.service;

import com.andre.order_api.entity.Order;
import com.andre.order_api.exception.ResourceNotFoundException;
import com.andre.order_api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order create(Order order) {
        try{
            log.info("Salvando pedido: %s", order.getCustomer().toString());
            return repository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar o pedido!");
        }
    }

    public List<Order> findAll() {
        List<Order> orders = repository.findAll();
        if (!orders.isEmpty()){
            return orders;
        } else {
            throw new ResourceNotFoundException("Nenhum pedido foi encontrado!");
        }
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
    }
}
