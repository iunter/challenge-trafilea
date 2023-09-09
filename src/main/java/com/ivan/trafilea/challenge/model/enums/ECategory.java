package com.ivan.trafilea.challenge.model.enums;

import com.ivan.trafilea.challenge.model.Order;
import com.ivan.trafilea.challenge.model.ProductCart;

import java.util.List;

public enum ECategory {
    COFFEE
            {
                @Override
                public Integer calculateDiscount(Order order) {
                    return null;
                }
            },
    EQUIPMENT
            {
                @Override
                public Integer calculateDiscount(Order order) {
                    return null;
                }
            },
    ACCESSORIES
            {
                @Override
                public Integer calculateDiscount(Order order) {
                    return null;
                }
            };

    public abstract Integer calculateDiscount(Order order);
}
