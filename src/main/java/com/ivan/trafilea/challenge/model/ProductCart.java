package com.ivan.trafilea.challenge.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
public class ProductCart
{

    @EmbeddedId
    private ProductCartKey productCartKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cartId")
    private Cart cart;

    private Integer quantity;

    public ProductCartKey getProductCartKey() {
        return productCartKey;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Boolean equals (ProductCart otherProduct)
    {
        return this.productCartKey == otherProduct.getProductCartKey();
    }
}
