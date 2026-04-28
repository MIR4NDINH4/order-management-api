package com.andre.order_api.controller;

import com.andre.order_api.entity.Customer;
import com.andre.order_api.exception.NotFoundException;
import com.andre.order_api.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {return customerService.create(customer);}

    @GetMapping("/getAll")
    public List<Customer> listAll() throws NotFoundException {
        return customerService.findAll();
    }

    @GetMapping("/getCustomerByEmail")
    public Customer listCustomerById(@RequestParam String email) throws NotFoundException {return customerService.findByEmail(email);}

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam Long id) { return customerService.deleteById(id);}
}
