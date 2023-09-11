package com.ivan.trafilea.challenge.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@Table(name = "cart")
public class Cart {

    @Id @GeneratedValue Long cartId;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
    private User user;

    private Boolean isActive;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @OneToOne(mappedBy = "cart")
    private Order order;

    public Cart(User user, List<CartItem> cartItems, Boolean isActive) {
        this.user = user;
        this.cartItems = cartItems;
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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addOrModifyProductCart(CartItem cartItem){
        Integer index = this.cartItems.indexOf(cartItem);
        if (index != -1)
        {
            this.cartItems.set(index, cartItem);
        } else
        {
            this.cartItems.add(cartItem);
        }
    }

}
