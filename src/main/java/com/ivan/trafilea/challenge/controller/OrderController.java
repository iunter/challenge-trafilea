package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.Order;
import com.ivan.trafilea.challenge.model.CartItem;
import com.ivan.trafilea.challenge.model.Product;
import com.ivan.trafilea.challenge.model.enums.ECategory;
import com.ivan.trafilea.challenge.service.CartService;
import com.ivan.trafilea.challenge.service.OrderService;
import com.ivan.trafilea.challenge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/{userId}")
    public Order placeOrder(@PathVariable String userId)
    {
        try
        {
            Cart cart = cartService.findByUser(userId);
            Order order = new Order();
            order.setCart(cart);

            order.setTotalProducts(calculateTotalProducts(cart));
            order.setTotalDiscounts(calculateDiscounts(cart));
            order.setTotalShipping(calculateShipping(cart));
            order.setTotalOrder(order.getTotalProducts() - order.getTotalDiscounts());

            cart.setActive(false);
            cartService.editCart(cart);

            return  orderService.placeOrder(order);
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
