package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.model.*;
import com.ivan.trafilea.challenge.service.CartService;
import com.ivan.trafilea.challenge.service.CartItemService;
import com.ivan.trafilea.challenge.service.ProductService;
import com.ivan.trafilea.challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private CartItemService cartItemService;

    public CartController(CartService cartService, UserService userService, ProductService productService, CartItemService cartItemService)
    {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @Operation(summary = "Creates a new cart for a user", description = "Returns a new cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "User does not exist"),
            @ApiResponse(responseCode = "401", description = "There is already an active cart for the user")
    })
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
    @Operation(summary = "Adds new items to or modifies existing ones inside a cart", description = "Returns the modified cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "User does not exist"),
            @ApiResponse(responseCode = "401", description = "An active cart for the user could not be found")
    })
    public ResponseEntity<Object> addOrModifyProduct(
            @RequestBody @Parameter(name = "ProdQuantityList", description = "A list with the product id and the desired quantity") List<ProdQuantity> prodQuantityList,
            @PathVariable @Parameter(name = "userId", description = "It utilizes the userId to find the active cart assigned to it") String userId)
    {
        try
        {
            Cart cart = cartService.findByUser(userId);
            for (ProdQuantity prodQuantity : prodQuantityList )
            {
                Product product = productService.findProductById(prodQuantity.getProductId());

                ProductCartKey key = new ProductCartKey(product.getProductId(), cart.getCartId());

                Optional<CartItem> optionalProductCart = cartItemService.findById(key);
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

                cartItemService.addOrModifyProductCart(cartItem);

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

