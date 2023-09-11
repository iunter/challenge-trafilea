package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.Order;
import com.ivan.trafilea.challenge.model.CartItem;
import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.model.enums.ECategory;
import com.ivan.trafilea.challenge.service.CartService;
import com.ivan.trafilea.challenge.service.OrderService;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Double BASE_SHIPPING = 10.0;
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    OrderService orderService;

    CartService cartService;

    UserService userService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService)
    {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Operation(summary = "Places an order from a given cart", description = "Returns the order, the cart gets deactivated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "User does not exist"),
            @ApiResponse(responseCode = "400", description = "An have been found placing an order")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<Object> placeOrder(
            @PathVariable @Parameter(name = "userId", description = "It utilizes the userId to find the active cart assigned to it") String userId)
    {
        try
        {
            Cart cart = cartService.findByUser(userId);
            Order order = new Order();
            order.setCart(cart);

            order.setTotalDiscounts(calculateDiscounts(cart));
            order.setTotalShipping(calculateShipping(cart));
            order.setTotalProducts(calculateTotalProducts(cart));
            order.setTotalOrder(order.getTotalProducts() - order.getTotalDiscounts());

            cart.setActive(false);
            cartService.editCart(cart);

            Order newOrder = orderService.placeOrder(order);
            return  ResponseEntity.status(HttpStatus.CREATED).body(order);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    private Double calculateTotalProducts(Cart cart)
    {
        Double total = 0.0;
        for (CartItem cartItem : cart.getCartItems())
        {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    private Double calculateDiscounts(Cart cart)
    {
        Double total = 0.0;

        for (ECategory category: getCategories(cart.getCartItems()))
        {
            total += category.calculateDiscount(cart);
        }

        return total;
    }

    private Double calculateShipping(Cart cart)
    {
        Double shipping = BASE_SHIPPING;

        for (ECategory category: getCategories(cart.getCartItems()))
        {
            Double newShipping = category.calculateShipping(cart, BASE_SHIPPING);
            shipping = Double.min(newShipping, shipping);
        }

        return shipping;
    }

    private List<ECategory> getCategories(List<CartItem> cartItems)
    {
        return cartItems.stream()
                .map(CartItem::getProduct)
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());

    }

}
