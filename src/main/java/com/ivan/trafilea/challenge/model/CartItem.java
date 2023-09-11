package com.ivan.trafilea.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem
{

    @EmbeddedId
    private ProductCartKey productCartKey;

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cartId")
    private Cart cart;

    private Integer quantity;

    public Boolean equals (CartItem otherProduct)
    {
        return this.productCartKey.equals(otherProduct.getProductCartKey());
    }
}
