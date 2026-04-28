package com.andre.order_api.service;

import com.andre.order_api.entity.Customer;
import com.andre.order_api.exception.NotFoundException;
import com.andre.order_api.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
       try{
           return customerRepository.save(customer);
       } catch (RuntimeException e) {
           throw new RuntimeException("Erro ao criar usuário" + e.getMessage());
       }
    }

    public List<Customer> findAll() throws NotFoundException {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException("Cliente não encontrado!");
        }
    }

    public Customer findByEmail(String email) throws NotFoundException {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public ResponseEntity<String> deleteById(Long id) {
        try{
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                return new ResponseEntity<String>("Excluído com sucesso: " + id, HttpStatus.OK);
            } else {
                throw new NotFoundException("Usuário não existe");
            }
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
