package com.andre.order_api.controller;

import com.andre.order_api.entity.Order;
import com.andre.order_api.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        log.info("Tentando criar um Order: " + order.toString());
        return service.create(order);
    }

    @GetMapping("/getAll")
    public List<Order> listAll() {
        return service.findAll();
    }

    @GetMapping("/getOrderById")
    public Order listOrderById(@RequestParam Long id) {
        return service.findById(id);
    }


}