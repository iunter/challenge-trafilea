package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.Order;
import com.ivan.trafilea.challenge.repository.IOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class OrderService {

    private IOrderRepository repository;

    public OrderService(IOrderRepository repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order)
    {
        try
        {
            return repository.save(order);
        }
        catch (Exception e)
        {
            throw new OrderServiceException(e.getMessage());
        }

    }
}

class OrderServiceException extends RuntimeException {
    OrderServiceException(String message) {
        super(message);
    }
}

@ControllerAdvice
class OrderServiceAdvice {

    @ResponseBody
    @ExceptionHandler(OrderServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String OrderServiceHandler(OrderServiceException ex) {
        return ex.getMessage();
    }
}

