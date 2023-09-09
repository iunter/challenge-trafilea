package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.ProductCart;
import com.ivan.trafilea.challenge.model.ProductCartKey;
import com.ivan.trafilea.challenge.repository.IProductCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCartService
{

    private IProductCartRepository repository;

    public ProductCartService(IProductCartRepository repository)
    {
        this.repository = repository;
    }

    public ProductCart addToCart(ProductCart productCart)
    {
        return repository.save(productCart);
    }

    public Optional<ProductCart> findById(ProductCartKey productCartKey)
    {
        return repository.findById(productCartKey);
    }
}

class ProductCartServiceException extends RuntimeException {
    ProductCartServiceException(String message) {
        super(message);
    }
}

