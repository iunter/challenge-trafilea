package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.repository.IProductRepository;
import org.springframework.stereotype.Service;

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
