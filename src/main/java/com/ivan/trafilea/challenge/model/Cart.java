package com.ivan.trafilea.challenge.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "cart")
public class Cart {

    @Id @GeneratedValue Long cartId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "userId", referencedColumnName = "userId")
    private User user;

    private Boolean isActive;

    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCarts;

    public Cart(User user, List<ProductCart> productCarts, Boolean isActive) {
        this.user = user;
        this.productCarts = productCarts;
        this.isActive = isActive;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }

    public void addOrModifyProductCart(ProductCart productCart){
        Integer index = this.productCarts.indexOf(productCart);
        if (index != -1)
        {
            this.productCarts.set(index, productCart);
        }
    }

}
