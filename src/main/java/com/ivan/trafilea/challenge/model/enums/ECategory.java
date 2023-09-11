package com.ivan.trafilea.challenge.model.enums;

import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.CartItem;
import com.ivan.trafilea.challenge.utils.DiscountStrategy;
import com.ivan.trafilea.challenge.utils.ShippingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public enum ECategory implements ShippingStrategy, DiscountStrategy {
    COFFEE
            {

                @Override
                public Double calculateDiscount(Cart cart) {
                    Double totalDiscount = 0.0;
                    for (CartItem cartItem : cart.getCartItems())
                    {
                        if(cartItem.getProduct().getCategory().equals(COFFEE))
                        {
                            if(cartItem.getQuantity() >= 2)
                            {
                                cartItem.setQuantity(cartItem.getQuantity() + 1);
                                totalDiscount += cartItem.getProduct().getPrice();
                            }
                        }
                    }

                    return totalDiscount;
                }
            },
    EQUIPMENT
            {
                Logger logger = LoggerFactory.getLogger(ECategory.class);
                @Override
                public Double calculateShipping(Cart cart, Double baseShipping) {
                    int equipmentCount = cart.getCartItems()
                            .stream()
                            .filter(cartItem -> EQUIPMENT.equals(cartItem.getProduct().getCategory()))
                            .mapToInt(CartItem::getQuantity)
                            .sum();

                    return (equipmentCount > 3) ? 0.0 : baseShipping;
                }
            },
    ACCESSORIES
            {
                @Override
                public Double calculateDiscount(Cart cart) {
                    double accessoriesTotal = cart.getCartItems()
                            .stream()
                            .filter(cartItem -> ACCESSORIES.equals(cartItem.getProduct().getCategory()))
                            .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                            .sum();

                    return (accessoriesTotal > 70.0) ? (accessoriesTotal * 0.10) : 0.0;

                }
            };

    @Override
    public Double calculateDiscount(Cart cart) {
        return 0.0;
    }


    @Override
    public Double calculateShipping(Cart cart, Double baseShipping) {
        return baseShipping;
    }
}
