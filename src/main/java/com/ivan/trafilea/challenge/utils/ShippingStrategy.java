package com.ivan.trafilea.challenge.utils;

import com.ivan.trafilea.challenge.model.Cart;

public interface ShippingStrategy {
    public Double calculateShipping(Cart cart, Double baseShipping);
}
