package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.CartItem;
import com.ivan.trafilea.challenge.model.ProductCartKey;
import com.ivan.trafilea.challenge.repository.IProductCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService
{

    private IProductCartRepository repository;

    public CartItemService(IProductCartRepository repository)
    {
        this.repository = repository;
    }

    public CartItem addOrModifyProductCart(CartItem cartItem)
    {
        return repository.save(cartItem);
    }

    public Optional<CartItem> findById(ProductCartKey productCartKey)
    {
        return repository.findById(productCartKey);
    }
}

class ProductCartServiceException extends RuntimeException {
    ProductCartServiceException(String message) {
        super(message);
    }
}

