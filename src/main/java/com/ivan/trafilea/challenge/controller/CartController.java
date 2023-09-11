package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.model.*;
import com.ivan.trafilea.challenge.service.CartService;
import com.ivan.trafilea.challenge.service.ProductCartService;
import com.ivan.trafilea.challenge.service.ProductService;
import com.ivan.trafilea.challenge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@ResponseBody
public class CartController
{
    Logger logger = LoggerFactory.getLogger(CartController.class);
    private CartService cartService;
    private UserService userService;
    private ProductService productService;

    private ProductCartService productCartService;

    public CartController(CartService cartService, UserService userService, ProductService productService, ProductCartService productCartService)
    {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.productCartService = productCartService;
    }

    //GETS CART FOR ONE USER
    @GetMapping("/{userId}")
    public Cart getUserCart(@PathVariable String userId)
    {
        return cartService.findByUser(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> newCart(@PathVariable String userId)
    {
        try
        {
            User user = userService.findById(userId);
            Cart cart = cartService.createCart(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> addOrModifyProduct(@RequestBody List<ProdQuantity> prodQuantityList, @PathVariable String userId)
    {
        try
        {
            Cart cart = cartService.findByUser(userId);
            for (ProdQuantity prodQuantity : prodQuantityList )
            {
                Product product = productService.findProductById(prodQuantity.getProductId());

                ProductCartKey key = new ProductCartKey(product.getProductId(), cart.getCartId());

                Optional<CartItem> optionalProductCart = productCartService.findById(key);
                CartItem cartItem = null;

                if (optionalProductCart.isPresent())
                {
                    cartItem = optionalProductCart.get();
                    cartItem.setQuantity(prodQuantity.getQuantity());
                }
                else
                {
                    cartItem = new CartItem(key, product, cart, prodQuantity.getQuantity());
                }

                cart.addOrModifyProductCart(cartItem);

                productCartService.addOrModifyProductCart(cartItem);

            }

            Cart newCart = cartService.editCart(cart);

            return  ResponseEntity.status(HttpStatus.OK).body(newCart);
        }
        catch (Exception e)
        {
            throw  e;
        }

    }

}

