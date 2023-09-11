package com.ivan.trafilea.challenge.utils;

import com.ivan.trafilea.challenge.model.Cart;

public interface DiscountStrategy {
    public Double calculateDiscount(Cart cart);
}
