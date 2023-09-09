package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.model.*;
import com.ivan.trafilea.challenge.service.CartService;
import com.ivan.trafilea.challenge.service.ProductCartService;
import com.ivan.trafilea.challenge.service.ProductService;
import com.ivan.trafilea.challenge.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController
{
    private CartService cartService;
    private UserService userService;
    private ProductService productService;

    private ProductCartService productCartService;

    public CartController(CartService cartService, UserService userService, ProductService productService)
    {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    //GETS CART FOR ONE USER
    @GetMapping("/{userId}")
    public Cart getUserCart(@PathVariable String userId)
    {
        return cartService.findByUser(userId);
    }

    @PostMapping
    public Cart newCart(@RequestBody String userId)
    {
        try
        {
            User user = userService.findById(userId);
            return cartService.createCart(user);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @PutMapping("/{userId}")
    public Cart addProduct(@RequestBody List<ProdQuantity> prodQuantityList, @PathVariable String userId)
    {

        List<Product> productList = Collections.emptyList();
        List<ProductCart> productCartList = Collections.emptyList();
        try
        {
            Cart cart = cartService.findByUser(userId);
            for (ProdQuantity prodQuantity : prodQuantityList )
            {
                Product product = productService.findProductById(prodQuantity.getProductId());

                ProductCartKey key = new ProductCartKey(product.getProductId(), cart.getCartId());

                Optional<ProductCart> optionalProductCart = productCartService.findById(key);
                ProductCart productCart = null;
                if (optionalProductCart.isPresent())
                {
                    productCart = optionalProductCart.get();
                    productCart.setQuantity(prodQuantity.getQuantity());
                }
                else
                {
                    productCart = new ProductCart(key, product, cart, prodQuantity.getQuantity());
                }

                productCartList.add(productCart);

                productCartService.addToCart(productCart);

            }
            cart.setProductCarts(productCartList);

            return  cartService.editCart(cart);
        }
        catch (Exception e)
        {
            throw  e;
        }

    }

}

