package com.andre.order_api.service;

import com.andre.order_api.entity.Customer;
import com.andre.order_api.exception.ResourceNotFoundException;
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

    public ResponseEntity<Customer> create(Customer customer) {
       try{
           customerRepository.save(customer);
           return ResponseEntity.ok(customer);
       } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(customer);
       }
    }

    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public ResponseEntity<String> deleteById(Long id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<String>("Excluído com sucesso: " + id, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<String>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
