package com.ivan.trafilea.challenge.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class ProductCartKey implements Serializable {

    @Column(name = "productId")
    private Long productId;

    @Column(name = "cartId")
    private Long cartId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Boolean equals (ProductCartKey otherProductCartKey)
    {
        return (this.productId == otherProductCartKey.getProductId() && this.getCartId() == otherProductCartKey.getCartId());
    }
}
