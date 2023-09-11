package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.Order;
import com.ivan.trafilea.challenge.repository.IOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private IOrderRepository repository;

    public OrderService(IOrderRepository repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order)
    {
        return repository.save(order);
    }
}

