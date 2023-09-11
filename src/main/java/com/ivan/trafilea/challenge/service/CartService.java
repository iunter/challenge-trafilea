package com.ivan.trafilea.challenge.service;

import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.User;
import com.ivan.trafilea.challenge.repository.ICartRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Optional;

@Service
public class CartService {
    private final ICartRepository repository;

    public CartService(ICartRepository repository) {
        this.repository = repository;
    }

    public Cart findByUser(String userId){
        return repository.findByUserId(userId).orElseThrow(() -> new CartServiceException("The cart assigned to user: " + userId + " could not be found"));
    }

    public Cart createCart(User user){
        Optional<Cart> cart = repository.findByUserId(user.getUserId());
        if(cart.isPresent()){
            if(cart.get().getActive()){
                throw new CartServiceException("There is already an active cart assigned to user: " + user.getUserId());
            }
        }
        return repository.save(new Cart(user, Collections.emptyList(), true));
    }

    public Cart editCart(Cart cart){
        return repository.save(cart);
    }

    public Cart disableCart(Cart cart){
        cart.setActive(false);
        return repository.save(cart);
    }
}

class CartServiceException extends RuntimeException {
    CartServiceException(String message) {
        super(message);
    }
}

@ControllerAdvice
class CartServiceAdvice {

    @ResponseBody
    @ExceptionHandler(CartServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String CartNotFoundHandler(CartServiceException ex) {
        return ex.getMessage();
    }
}