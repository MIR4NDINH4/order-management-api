package com.andre.order_api.service;

import com.andre.order_api.entity.Order;
import com.andre.order_api.exception.NotFoundException;
import com.andre.order_api.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    public Order create(Order order) {
        try{
            log.info("Tentando salvar pedido: " + order.toString());
            boolean existis = customerRepository.existsByNameAndEmail(order.getCustomer().getName(), order.getCustomer().getEmail());
            if (existis) {
                log.info("Salvando pedido: " + order.getCustomer().toString());
                return repository.save(order);
            } else {
                log.info("Usuário não encontrado: " + order.getCustomer().toString());
                throw new NotFoundException("Usuário não existe");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao salvar o pedido!");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findAll() throws NotFoundException {
        List<Order> orders = repository.findAll();
        if (!orders.isEmpty()){
            return orders;
        } else {
            throw new NotFoundException("Nenhum pedido foi encontrado!");
        }
    }

    public Order findById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
    }
}
