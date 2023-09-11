package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.repository.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ProductService {
    private IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    public Product findProductById (Long productId){
        return repository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product with Id: " + productId + " could not be found"));
    }
}

class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(String message) {
        super(message);
    }
}

@ControllerAdvice
class ProductNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ProductNotFoundHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }
}
