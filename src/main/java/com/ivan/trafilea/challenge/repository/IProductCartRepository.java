package com.ivan.trafilea.challenge.repository;

import com.ivan.trafilea.challenge.model.CartItem;
import com.ivan.trafilea.challenge.model.ProductCartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductCartRepository extends JpaRepository<CartItem, ProductCartKey> {

}
